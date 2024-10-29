package com.study.jimcarry.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReqQuotationService {
	
    private final ReqQuotationMapper reqQuotationMapper;
    
    private final MoveItemMapper moveItemMapper;
	/**
	 * 견적요청서 작성
	 * @param reqQuotationEntity
	 * @return
	 */
    @Transactional
	public int saveReqQuotation(ReqQuotationDTO reqQuotation, List<MoveItemDTO> moveItemList) {
		
		  // UUID 생성 (유일한 식별자)
	    String uuid = UUID.randomUUID().toString();

	    // ID 조합: "ReqQuo_UUID ReqQu0_ 제거"
	    String generatedId = "ReqQuo_" + uuid;

	    // UUID로 조회하여 이미 존재하는지 확인
	    Optional<ReqQuotationEntity> optionalReqQuotation = 
	        Optional.ofNullable(reqQuotationMapper.selectReqQuotation(generatedId));

	    // 만약 존재한다면 예외 발생
	    optionalReqQuotation.ifPresent(req -> {
	        throw new CustomException(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
	    });
	    
	    // 빌더 패턴을 사용하여 reqQuotationEntity 생성
	    ReqQuotationEntity reqQuotationEntity = ReqQuotationEntity.builder()
	    		.quotationReqNo(generatedId)
	    		.quotationDt(reqQuotation.getQuotationDt())
	    		.custId(reqQuotation.getCustId())
	    		.pickupAddr(reqQuotation.getPickupAddr())
	    		.deliveryAddr(reqQuotation.getDeliveryAddr())
	    		.moveDt(reqQuotation.getMoveDt())
	    		.buildingType(reqQuotation.getBuildingType())
	    		.roomStructure(reqQuotation.getRoomStructure())
	    		.houseSize(reqQuotation.getHouseSize())
	    		.hasElevator(reqQuotation.isHasElevator())
	    		.boxCount(reqQuotation.getBoxCount())
	    		.quotationAmount(reqQuotation.getQuotationAmount())
	    		.cid(0)
	    		.build();

	    for(MoveItemDTO dto : moveItemList) {
	    	MoveItemEntity entity = MoveItemEntity.builder()
    			.quotationReqNo(generatedId)
    			.furnitureId(dto.getFurnitureId())
    			.optionValId(dto.getOptionValId())
    			.qty(dto.getQty())
    			.cid(0)
    			.build();
	    		
	    	moveItemMapper.insertMoveItem(entity);
	    }
	    
	    
	    // 레코드 저장
	    return reqQuotationMapper.insertReqQuotation(reqQuotationEntity);
	}

	/**
	 * 견적요청서 수정
	 * @param reqQuotationEntity
	 * @return
	 */
	public int modifyReqQuotation(ReqQuotationDTO reqQuotation) {	
		//find 변수명 뺴기
		ReqQuotationEntity entity = reqQuotationMapper.selectReqQuotation(reqQuotation.getQuotationReqNo());
		if(entity == null) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
		}
		
	    // 빌더 패턴을 사용하여 reqQuotationEntity 생성, Update를 위한 DTO를 생성 해서 entity를 황용 한뒤 Mapper로 넘긴다.
	    ReqQuotationEntity reqQuotationEntity = ReqQuotationEntity.builder()
	    		.quotationReqNo(reqQuotation.getQuotationReqNo())
	    		.quotationDt(reqQuotation.getQuotationDt())
	    		.custId(reqQuotation.getCustId())
	    		.pickupAddr(reqQuotation.getPickupAddr())
	    		.deliveryAddr(reqQuotation.getDeliveryAddr())
	    		.moveDt(reqQuotation.getMoveDt())
	    		.buildingType(reqQuotation.getBuildingType())
	    		.roomStructure(reqQuotation.getRoomStructure())
	    		.houseSize(reqQuotation.getHouseSize())
	    		.hasElevator(reqQuotation.isHasElevator())
	    		.boxCount(reqQuotation.getBoxCount())
	    		.quotationAmount(reqQuotation.getQuotationAmount())
	    		.build();
	    
		return reqQuotationMapper.updateReqQuotation(reqQuotationEntity);
	}
	
	/**
	 * 견적요청서 삭제
	 * @param reqQuotationId
	 * @return
	 */
	public int deleteReqQuotation(String reqQuotationId) {
		return reqQuotationMapper.deleteReqQuotation(reqQuotationId);
	}
	
	/**
	 * 견적요청서 전체 조회
	 * @return
	 */
	public List<ReqQuotationDTO> getReqQuotationList() {
		List<ReqQuotationEntity> findList =  reqQuotationMapper.selectAllReqQuotations();
		List<ReqQuotationDTO> reqQuotationList = new ArrayList<>();
		for(ReqQuotationEntity entity : findList) {
			ReqQuotationDTO reqQuotation = ReqQuotationDTO.builder()
		    		.quotationReqNo(entity.getQuotationReqNo())
		    		.quotationDt(entity.getQuotationDt())
		    		.custId(entity.getCustId())
		    		.pickupAddr(entity.getPickupAddr())
		    		.deliveryAddr(entity.getDeliveryAddr())
		    		.moveDt(entity.getMoveDt())
		    		.buildingType(entity.getBuildingType())
		    		.roomStructure(entity.getRoomStructure())
		    		.houseSize(entity.getHouseSize())
		    		.hasElevator(entity.isHasElevator())
		    		.boxCount(entity.getBoxCount())
		    		.quotationAmount(entity.getQuotationAmount())
		    		.build();
			reqQuotationList.add(reqQuotation);
		}
		return reqQuotationList;
	}
	
	/**
	 * 사용자별 견적요청서 조회
	 * @param customerId
	 * @return
	 */
	public ReqQuotationDTO getReqQuotationByUser(String customerId) {
		ReqQuotationEntity entity = reqQuotationMapper.selectReqQuotationByUser(customerId);
		if(entity == null) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
		}
		ReqQuotationDTO reqQuotation = ReqQuotationDTO.builder()
	    		.quotationReqNo(entity.getQuotationReqNo())
	    		.quotationDt(entity.getQuotationDt())
	    		.custId(entity.getCustId())
	    		.pickupAddr(entity.getPickupAddr())
	    		.deliveryAddr(entity.getDeliveryAddr())
	    		.moveDt(entity.getMoveDt())
	    		.buildingType(entity.getBuildingType())
	    		.roomStructure(entity.getRoomStructure())
	    		.houseSize(entity.getHouseSize())
	    		.hasElevator(entity.isHasElevator())
	    		.boxCount(entity.getBoxCount())
	    		.quotationAmount(entity.getQuotationAmount())
	    		.build();
		return reqQuotation;
	}
	
	/**
	 * 견적 채택상태 갱신
	 * @param reqQuotationId
	 * @param isAccepted
	 * @return
	 */
	public int updateReqQuotationIsAccepted(String reqQuotationId, Boolean isAccepted) {
		ReqQuotationEntity findReqQuotationEntity = reqQuotationMapper.selectReqQuotation(reqQuotationId);
		if(findReqQuotationEntity == null) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
		}
		return reqQuotationMapper.updateReqQuotationIsAccepted(reqQuotationId, isAccepted);
	}

}
