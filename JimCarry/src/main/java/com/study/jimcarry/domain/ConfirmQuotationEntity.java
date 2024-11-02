package com.study.jimcarry.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

/**
 * 견적확정 테이블
 */
@Getter
@Builder
public class ConfirmQuotationEntity {
	
	// 견적요청 번호
	private String quotationReqNo;
	
	// 고객 아이디
	private String custId;
	
	// 기사님 아이디
	private String driverId;
	
	// 견적확정일시
	private Date confirmDt;

	// 생성자
	private Integer cid;
	
}
