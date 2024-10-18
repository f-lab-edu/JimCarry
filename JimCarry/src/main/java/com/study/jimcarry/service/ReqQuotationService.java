package com.study.jimcarry.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.study.jimcarry.domain.ReqQuotationEntity;
import com.study.jimcarry.exception.CustomException;
import com.study.jimcarry.exception.ErrorCode;
import com.study.jimcarry.mapper.MovingInfoMapper;
import com.study.jimcarry.mapper.ReqQuotationMapper;
import com.study.jimcarry.model.ReqQuotation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReqQuotationService {
	
    private final ReqQuotationMapper reqQuotationMapper;
    
	/**
	 * 견적요청서 작성
	 * @param reqQuotationEntity
	 * @return
	 */
	public int saveReqQuotation(ReqQuotation reqQuotation) {
	    // 최대 ID 가져오기
	    int maxId = reqQuotationMapper.selectReqQuotationMaxId();

	    // 날짜 형식 설정 (YYYYMMDD)
	    String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());

	    // StringBuilder를 사용하여 ID 조합: "ReqQuoYYYYMMDD_Number"
	    StringBuilder generatedIdBuilder = new StringBuilder();
	    generatedIdBuilder.append("ReqQuo")
	                      .append(currentDate)
	                      .append("_")
	                      .append(maxId + 1);

	    // 조합된 문자열을 ID로 설정
	    String generatedId = generatedIdBuilder.toString();
	    
	    // 빌더 패턴을 사용하여 reqQuotationEntity 생성
	    ReqQuotationEntity reqQuotationEntity = ReqQuotationEntity.builder()
	    		.reqQuotationId(generatedId)
	    		.reqQuotationDt(reqQuotation.getReqQuotationDt())
	    		.customerId(reqQuotation.getCustomerId())
	    		.departureAddress(reqQuotation.getDepartureAddress())
	    		.destinationAddress(reqQuotation.getDestinationAddress())
	    		.movingDate(reqQuotation.getMovingDate())
	    		.buildingType(reqQuotation.getBuildingType())
	    		.roomStructure(reqQuotation.getRoomStructure())
	    		.houseArea(reqQuotation.getHouseArea())
	    		.hasElevator(reqQuotation.isHasElevator())
	    		.hasParking(reqQuotation.isHasParking())
	    		.boxCount(reqQuotation.getBoxCount())
	    		.requestedEstimate(reqQuotation.getRequestedEstimate())
	    		.isAccepted(reqQuotation.isAccepted())
	    		.build();

	    //TODO 이삿 짐 정보 저장 로직 추가 예정
	    //request에서 이상 짐 정보를 List로 받아와서 for문을 돌릴지 고민중..
	    
	    // 레코드 저장
	    return reqQuotationMapper.insertReqQuotation(reqQuotationEntity);
	}

	/**
	 * 견적요청서 수정
	 * @param reqQuotationEntity
	 * @return
	 */
	public int modifyReqQuotation(ReqQuotation reqQuotation) {	
		ReqQuotationEntity findReqQuotationEntity = reqQuotationMapper.selectReqQuotation(reqQuotation.getReqQuotationId());
		if(findReqQuotationEntity == null) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
		}
		
	    // 빌더 패턴을 사용하여 reqQuotationEntity 생성
	    ReqQuotationEntity reqQuotationEntity = ReqQuotationEntity.builder()
	    		.reqQuotationId(reqQuotation.getReqQuotationId())
	    		.reqQuotationDt(reqQuotation.getReqQuotationDt())
	    		.customerId(reqQuotation.getCustomerId())
	    		.departureAddress(reqQuotation.getDepartureAddress())
	    		.destinationAddress(reqQuotation.getDestinationAddress())
	    		.movingDate(reqQuotation.getMovingDate())
	    		.buildingType(reqQuotation.getBuildingType())
	    		.roomStructure(reqQuotation.getRoomStructure())
	    		.houseArea(reqQuotation.getHouseArea())
	    		.hasElevator(reqQuotation.isHasElevator())
	    		.hasParking(reqQuotation.isHasParking())
	    		.boxCount(reqQuotation.getBoxCount())
	    		.requestedEstimate(reqQuotation.getRequestedEstimate())
	    		.isAccepted(reqQuotation.isAccepted())
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
	public List<ReqQuotation> getReqQuotationList() {
		List<ReqQuotationEntity> findList =  reqQuotationMapper.selectAllReqQuotations();
		List<ReqQuotation> reqQuotationList = new ArrayList<>();
		for(ReqQuotationEntity entity : findList) {
			ReqQuotation reqQuotation = ReqQuotation.builder()
		    		.reqQuotationId(entity.getReqQuotationId())
		    		.reqQuotationDt(entity.getReqQuotationDt())
		    		.customerId(entity.getCustomerId())
		    		.departureAddress(entity.getDepartureAddress())
		    		.destinationAddress(entity.getDestinationAddress())
		    		.movingDate(entity.getMovingDate())
		    		.buildingType(entity.getBuildingType())
		    		.roomStructure(entity.getRoomStructure())
		    		.houseArea(entity.getHouseArea())
		    		.hasElevator(entity.isHasElevator())
		    		.hasParking(entity.isHasParking())
		    		.boxCount(entity.getBoxCount())
		    		.requestedEstimate(entity.getRequestedEstimate())
		    		.isAccepted(entity.isAccepted())
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
	public ReqQuotation getReqQuotationByUser(String customerId) {
		ReqQuotationEntity entity = reqQuotationMapper.selectReqQuotationByUser(customerId);
		if(entity == null) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
		}
		ReqQuotation reqQuotation = ReqQuotation.builder()
	    		.reqQuotationId(entity.getReqQuotationId())
	    		.reqQuotationDt(entity.getReqQuotationDt())
	    		.customerId(entity.getCustomerId())
	    		.departureAddress(entity.getDepartureAddress())
	    		.destinationAddress(entity.getDestinationAddress())
	    		.movingDate(entity.getMovingDate())
	    		.buildingType(entity.getBuildingType())
	    		.roomStructure(entity.getRoomStructure())
	    		.houseArea(entity.getHouseArea())
	    		.hasElevator(entity.isHasElevator())
	    		.hasParking(entity.isHasParking())
	    		.boxCount(entity.getBoxCount())
	    		.requestedEstimate(entity.getRequestedEstimate())
	    		.isAccepted(entity.isAccepted())
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
