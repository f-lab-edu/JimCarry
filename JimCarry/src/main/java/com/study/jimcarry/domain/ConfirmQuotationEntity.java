package com.study.jimcarry.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.study.jimcarry.domain.ReqQuotationEntity.ReqQuotationEntityBuilder;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * 견적확정 테이블
 */
@Getter
@Builder
public class ConfirmQuotationEntity {
	
	// 견적요청 번호
	private String quotationReqNo;
	
	// 견적확정일시
	private LocalDateTime confirmDt;

	// 고객 아이디
	private String custId;
	
	// 기사님 아이디
	private String driverId;

	// 생성자
	private Integer cid;
	
}
