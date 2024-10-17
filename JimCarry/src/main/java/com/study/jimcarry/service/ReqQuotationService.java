package com.study.jimcarry.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.study.jimcarry.domain.ReqQuotationEntity;
import com.study.jimcarry.model.ReqQuotation;

@Service
public class ReqQuotationService {
	
	/**
	 * 견적요청서 작성
	 * @param reqQuotationEntity
	 * @return
	 */
	int saveReqQuotation(ReqQuotationEntity reqQuotationEntity);

	/**
	 * 견적요청서 수정
	 * @param reqQuotationEntity
	 * @return
	 */
	int modifyReqQuotation(ReqQuotationEntity reqQuotationEntity);
	
	/**
	 * 견적요청서 삭제
	 * @param reqQuotationId
	 * @return
	 */
	int deleteReqQuotation(String reqQuotationId);
	
	/**
	 * 견적요청서 전체 조회
	 * @return
	 */
	List<ReqQuotation> getReqQuotationList();
	
	/**
	 * 사용자별 견적요청서 조회
	 * @param customerId
	 * @return
	 */
	ReqQuotation getReqQuotationByUser(String customerId);
	
	/**
	 * 견적 채택상태 갱신
	 * @param reqQuotationId
	 * @param isAccepted
	 * @return
	 */
	int updateReqQuotationIsAccepted(String reqQuotationId, Boolean isAccepted);

}
