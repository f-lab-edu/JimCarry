package com.study.jimcarry.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReqQuotationEntity {
	
	// 견적요청_ID
	private String reqQuotationId;

	// 견적확정 일시
	private LocalDateTime confirmQuotationDt;

	// 기사님 아이디
	private String driverId;

	// 생성자
	private String ctr;
}
