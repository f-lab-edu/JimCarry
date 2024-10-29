package com.study.jimcarry.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.jimcarry.domain.QuotationAcceptEntity;

@Mapper
public interface QuotationAcceptMapper {
	
	/**
	 * 이사 채택
	 * @param QuotationAcceptEntity
	 * @return
	 */
	int insertQuotationAccept(QuotationAcceptEntity entity);
	
	/**
	 * 이사내역 전체 조회
	 * @return
	 */
	List<QuotationAcceptEntity> selectQuotationAcceptList();
	
	/**
	 * 고객별 이사내역 조회
	 * @param customerId
	 * @return
	 */
	QuotationAcceptEntity selectQuotationAcceptByCustomers(String customerId);
	
	/**
	 * 기사님별 이사내역 조회
	 * @param driverId
	 * @return
	 */
	QuotationAcceptEntity selectQuotationAcceptByDrivers(String driverId);
}
