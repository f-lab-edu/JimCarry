package com.study.jimcarry.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.uuid.Generators;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.jimcarry.domain.MoveItemEntity;
import com.study.jimcarry.domain.ReqQuotationEntity;
import com.study.jimcarry.exception.CustomException;
import com.study.jimcarry.exception.ErrorCode;
import com.study.jimcarry.mapper.MoveItemMapper;
import com.study.jimcarry.mapper.ReqQuotationMapper;
import com.study.jimcarry.model.MoveItemDTO;
import com.study.jimcarry.model.ReqQuotationDTO;
import com.study.jimcarry.model.UpdateReqQuotationDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReqQuotationService {

	private final ReqQuotationMapper reqQuotationMapper;

	private final MoveItemMapper moveItemMapper;

	private static final int MAX_RETRIES = 3; // 최대 재시도 횟수

	/**
	 * 견적요청서 작성
	 * 
	 * @param reqQuotationEntity
	 * @return
	 */
	@Transactional
	public int saveReqQuotation(ReqQuotationDTO reqQuotation, List<MoveItemDTO> moveItemList) {

		// UUID Version1 생성
		// 타임 스탬프 기반으로 생성 되며 시간 순서대로 정렬이 됨.
		String uuidVer1 = Generators.timeBasedGenerator().generate().toString();
		log.debug("uuid ver1 -> {}", uuidVer1);

		// UUID version4 생성
		// version4는 정렬되지 않은 UUID로 PK로 사용할 경우
		// 삽입, 조회 성능에 영향을 미침
		String generatedId = UUID.randomUUID().toString();
		log.debug("uuid ver4 -> {}", generatedId);

		// 빌더 패턴을 사용하여 reqQuotationEntity 생성
		ReqQuotationEntity reqQuotationEntity = ReqQuotationEntity.builder().quotationReqNo(generatedId)
				.custId(reqQuotation.getCustId()).pickupAddr(reqQuotation.getPickupAddr())
				.deliveryAddr(reqQuotation.getDeliveryAddr()).moveDt(reqQuotation.getMoveDt())
				.buildingType(reqQuotation.getBuildingType()).roomStructure(reqQuotation.getRoomStructure())
				.houseSize(reqQuotation.getHouseSize()).hasElevator(reqQuotation.isHasElevator())
				.boxCount(reqQuotation.getBoxCount()).quotationAmount(reqQuotation.getQuotationAmount()).cid(0).build();

		for (MoveItemDTO dto : moveItemList) {
			MoveItemEntity entity = MoveItemEntity.builder().quotationReqNo(generatedId)
					.furnitureId(dto.getFurnitureId()).optionValId(dto.getOptionValId()).qty(dto.getQty()).cid(0)
					.build();
			log.debug("move Item -> {}", entity);
			moveItemMapper.insertMoveItem(entity);
		}

		// 레코드 저장
		return reqQuotationMapper.insertReqQuotation(reqQuotationEntity);
	}

	/**
	 * 견적요청서 수정
	 * 
	 * @param reqQuotationEntity
	 * @return
	 */
	@Transactional
	public int modifyReqQuotation(UpdateReqQuotationDTO updateReqQuotation, String quotationId) {
	    for (int attempt = 0; attempt < MAX_RETRIES; attempt++) {
	        // 견적 정보를 조회합니다.
	        Optional<ReqQuotationEntity> optionalEntity = Optional.ofNullable(reqQuotationMapper.selectReqQuotation(quotationId));
	        ReqQuotationEntity entity = optionalEntity.orElseThrow(
	                () -> new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));

	        // 견적 상태가 "0"이 아닐 경우 예외를 발생시킵니다.
	        if (!"0".equals(entity.getStatus())) {
	            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "이미 확정되거나 채택 된 견적 입니다.");
	        }
	        log.debug("version => {}", entity.getVersion());
	        // 빌더 패턴을 사용하여 업데이트할 엔티티를 생성합니다.
	        entity = ReqQuotationEntity.builder()
	                .quotationReqNo(quotationId)
	                .pickupAddr(updateReqQuotation.getPickupAddr())
	                .deliveryAddr(updateReqQuotation.getDeliveryAddr())
	                .moveDt(updateReqQuotation.getMoveDt())
	                .buildingType(updateReqQuotation.getBuildingType())
	                .roomStructure(updateReqQuotation.getRoomStructure())
	                .houseSize(updateReqQuotation.getHouseSize())
	                .hasElevator(updateReqQuotation.isHasElevator())
	                .boxCount(updateReqQuotation.getBoxCount())
	                .quotationAmount(updateReqQuotation.getQuotationAmount())
	                .cid(0) // 실제 cid 값으로 대체해야 함
	                .version(entity.getVersion())
	                .build();

	        // 업데이트를 시도합니다.
	        int resultRow = reqQuotationMapper.updateReqQuotation(entity);
	        
	        // 업데이트 성공 시 결과를 반환합니다.
	        if (resultRow == 1) {
	            return resultRow; // 성공적으로 업데이트 되었을 때
	        } else {
	            log.warn("Optimistic locking failure, attempt: {}", attempt + 1);
	            // 재시도를 위한 로깅
	        }
	    }

	    // 모든 시도 후 업데이트 실패 시 예외를 발생시킵니다.
	    throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "수정 실패: 다른 사용자가 먼저 변경하였습니다.");
	}

	/**
	 * 견적요청서 삭제
	 * 
	 * @param reqQuotationId
	 * @return
	 */
	public int deleteReqQuotation(String quotationId) {

		Optional<ReqQuotationEntity> optionalEntity = Optional
				.ofNullable(reqQuotationMapper.selectReqQuotation(quotationId));
		ReqQuotationEntity entity = optionalEntity.orElseThrow(
				() -> new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));

		if (!"0".equals(entity.getStatus())) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "이미 확정되거나 채택 된 견적 입니다.");
		}
		return reqQuotationMapper.deleteReqQuotation(quotationId);
	}

	/**
	 * 견적요청서 전체 조회
	 * 
	 * @return
	 */
	public List<ReqQuotationDTO> getReqQuotationList() {
		List<ReqQuotationEntity> findList = reqQuotationMapper.selectAllReqQuotations();
		List<ReqQuotationDTO> reqQuotationList = new ArrayList<>();
		for (ReqQuotationEntity entity : findList) {
			ReqQuotationDTO reqQuotation = ReqQuotationDTO.builder().quotationReqNo(entity.getQuotationReqNo())
					.custId(entity.getCustId()).pickupAddr(entity.getPickupAddr())
					.deliveryAddr(entity.getDeliveryAddr()).moveDt(entity.getMoveDt())
					.buildingType(entity.getBuildingType()).roomStructure(entity.getRoomStructure())
					.houseSize(entity.getHouseSize()).hasElevator(entity.isHasElevator()).boxCount(entity.getBoxCount())
					.quotationAmount(entity.getQuotationAmount()).status(entity.getStatus()).build();
			reqQuotationList.add(reqQuotation);
		}
		return reqQuotationList;
	}

	/**
	 * 사용자별 견적요청서 조회
	 * 
	 * @param customerId
	 * @return
	 */
	public ReqQuotationDTO getReqQuotationByUser(String customerId) {

		Optional<ReqQuotationEntity> optionalEntity = Optional
				.ofNullable(reqQuotationMapper.selectReqQuotationByUser(customerId));
		ReqQuotationEntity entity = optionalEntity.orElseThrow(
				() -> new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));

		ReqQuotationDTO reqQuotation = ReqQuotationDTO.builder().quotationReqNo(entity.getQuotationReqNo())
				.custId(entity.getCustId()).pickupAddr(entity.getPickupAddr()).deliveryAddr(entity.getDeliveryAddr())
				.moveDt(entity.getMoveDt()).buildingType(entity.getBuildingType())
				.roomStructure(entity.getRoomStructure()).houseSize(entity.getHouseSize())
				.hasElevator(entity.isHasElevator()).boxCount(entity.getBoxCount())
				.quotationAmount(entity.getQuotationAmount()).status(entity.getStatus()).build();
		return reqQuotation;
	}

	/**
	 * 견적상태 갱신
	 * 
	 * @param reqQuotationId
	 * @param status
	 * @return
	 */
	public int updateReqQuotationStatus(String reqQuotationId, String status) {

		Optional<ReqQuotationEntity> optionalEntity = Optional
				.ofNullable(reqQuotationMapper.selectReqQuotation(reqQuotationId));
		optionalEntity.orElseThrow(
				() -> new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));

		return reqQuotationMapper.updateReqQuotationStatus(reqQuotationId, status);
	}

}
