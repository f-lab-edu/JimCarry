package com.study.jimcarry.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.jimcarry.domain.ConfirmQuotationEntity;
import com.study.jimcarry.domain.ReqQuotationEntity;

@Mapper
public interface ConfirmQuotationMapper {
	
	/**
	 * 견적확정 정보 저장
	 * @param confirmQuotationEntity
	 * @return
	 */
	int insertConfirmQuotation(ConfirmQuotationEntity confirmQuotationEntity);
	
	/**
	 * 견적확정 정보 수정
	 * @param confirmQuotationEntity
	 * @return
	 */
	int updateConfirmQuotation(ConfirmQuotationEntity confirmQuotationEntity);
	
	/**
	 * 견적 확정정보 삭제(철회)
	 * @param reqQuotationId
	 * @return
	 */
	int deleteConfirmQuotation(String reqQuotationId);
	
	/**
	 * 기사님별 견적확정 정보 조회
	 * @param driverId
	 * @return
	 */
	List<ConfirmQuotationEntity> selectConfirmQuotationByDriver(String driverId);
	
	/**
	 * 사용자별 견적확정 정보 조회
	 * @param customerId
	 * @return
	 */
	ConfirmQuotationEntity selectConfirmQuotationByUser(String customerId);
	
	/**
	 * 견적확정 정보 조회
	 * @param reqQuotationId
	 * @return
	 */
	ConfirmQuotationEntity selectConfrimQuotation(String reqQuotationId);
}
