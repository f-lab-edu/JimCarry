package com.study.jimcarry.service;

import java.util.List;

import com.study.jimcarry.domain.ConfirmQuotationEntity;
import com.study.jimcarry.model.ConfirmQuotation;

public class ConfirmQuotationService {
	
	/**
	 * 견적확정 정보 저장 
	 * @param confirmQuotationEntity
	 * @return
	 */
	int saveConfirmQuotation(ConfirmQuotationEntity confirmQuotationEntity);

	/**
	 * 견적확정 정보 수정
	 * @param confirmQuotationEntity
	 * @return
	 */
	int modifyConfrimQuotation(ConfirmQuotationEntity confirmQuotationEntity);
	
	/**
	 * 견적 확정정보 삭제(철회)
	 * @param reqQuotationId
	 * @return
	 */
	int deleteConfirmQuotation(String reqQuotationId);
	
	/**
	 * 기사님별 견적확정 정보 조회
	 * @return
	 */
	List<ConfirmQuotation> getConfirmQuotationListByDriver(String driverId);
	
	/**
	 * 사용자별 견적확정 정보 조회
	 * @param customerId
	 * @return
	 */
	ConfirmQuotation getConfirmQuotationByUser(String customerId);
	
}
