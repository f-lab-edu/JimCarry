package com.study.jimcarry.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.jimcarry.domain.ReqQuotationEntity;
import com.study.jimcarry.exception.CustomException;
import com.study.jimcarry.exception.ErrorCode;
import com.study.jimcarry.mapper.ReqQuotationMapper;
import com.study.jimcarry.model.ReqQuotation;
import com.study.jimcarry.service.ReqQuotationService;

@Service
public class ReqQuotationServiceImpl implements ReqQuotationService {

    private final ReqQuotationMapper reqQuotationMapper;
    private final ModelMapper modelMapper;

    public ReqQuotationServiceImpl(ReqQuotationMapper reqQuotationMapper, ModelMapper modelMapper) {
        this.reqQuotationMapper = reqQuotationMapper;
        this.modelMapper = modelMapper;
    }
	
	@Override
	public int saveReqQuotation(ReqQuotationEntity reqQuotationEntity) {
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
	    reqQuotationEntity = reqQuotationEntity.toBuilder()
				            .reqQuotationId(generatedId)
				            .build();
	    
	    //TODO 이삿 짐 정보 저장 로직 추가 예정
	    //request에서 이상 짐 정보를 List로 받아와서 for문을 돌릴지 고민중..
	    
	    // 레코드 저장
	    return reqQuotationMapper.insertReqQuotation(reqQuotationEntity);
	}

	@Override
	public int modifyReqQuotation(ReqQuotationEntity reqQuotationEntity) {	
		ReqQuotationEntity findReqQuotationEntity = reqQuotationMapper.selectReqQuotation(reqQuotationEntity.getReqQuotationId());
		if(findReqQuotationEntity == null) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
		}
		return reqQuotationMapper.updateReqQuotation(reqQuotationEntity);
	}

	@Override
	public List<ReqQuotation> getReqQuotationList() {
		List<ReqQuotationEntity> findList =  reqQuotationMapper.selectAllReqQuotations();
		List<ReqQuotation> reqQuotationList = new ArrayList<>();
		for(ReqQuotationEntity entity : findList) {
			ReqQuotation reqQuotation = modelMapper.map(entity, ReqQuotation.class);
			reqQuotationList.add(reqQuotation);
		}
		return reqQuotationList;
	}

	@Override
	public int deleteReqQuotation(String reqQuotationId) {
		return reqQuotationMapper.deleteReqQuotation(reqQuotationId);
	}

	@Override
	public ReqQuotation getReqQuotationByUser(String customerId) {
		ReqQuotationEntity findReqQuotationEntity = reqQuotationMapper.selectReqQuotationByUser(customerId);
		if(findReqQuotationEntity == null) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
		}
		ReqQuotation reqQuotation = modelMapper.map(findReqQuotationEntity, ReqQuotation.class);
		return reqQuotation;
	}

	@Override
	public int updateReqQuotationIsAccepted(String reqQuotationId, Boolean isAccepted) {
		ReqQuotationEntity findReqQuotationEntity = reqQuotationMapper.selectReqQuotation(reqQuotationId);
		if(findReqQuotationEntity == null) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
		}
		return reqQuotationMapper.updateReqQuotationIsAccepted(reqQuotationId, isAccepted);
	}
}
