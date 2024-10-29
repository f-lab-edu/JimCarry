package com.study.jimcarry.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.jimcarry.domain.ReqQuotationEntity;

@Mapper
public interface ReqQuotationMapper {
	
	/**
	 * 견적요청서 저장
	 * @param reqQuotationEntity
	 * @return
	 */
	int insertReqQuotation(ReqQuotationEntity reqQuotationEntity);
	
	/**
	 * 견적요청서 수정
	 * @param reqQuotationEntity
	 * @return
	 */
	int updateReqQuotation(ReqQuotationEntity reqQuotationEntity);
	
	/**
	 * 견적요청서 삭제
	 * @param reqQuotationId
	 * @return
	 */
	int deleteReqQuotation(String reqQuotationId);
	
	/**
	 * 견적 요청서 전체 조회
	 * @return
	 */
	List<ReqQuotationEntity> selectAllReqQuotations();
	
	/**
	 * 사용자별 견적요청서 조회
	 * @param customerId
	 * @return
	 */
	ReqQuotationEntity selectReqQuotationByUser(String customerId);
	
	/**
	 * 견적 채택상태 갱신
	 * @param reqQuotationId
	 * @param isAccepted
	 * @return
	 */
	int updateReqQuotationIsAccepted(String reqQuotationId, Boolean isAccepted);
	
	/**
	 * 견적요청서 조회
	 * @param customerId
	 * @return
	 */
	ReqQuotationEntity selectReqQuotation(String reqQuotationId);
}
