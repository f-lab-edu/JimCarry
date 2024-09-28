package com.study.jimcarry.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ConfirmQuotationEntity {
	
	// 견적요청_ID
	private String reqQuotationId;

	// 견적확정일시
	private LocalDateTime confirmQuotationDt;

	// 고객 아이디
	private String customerId;
	
	// 기사님 아이디
	private String driverId;

	// 생성자
	private String ctr;
}
