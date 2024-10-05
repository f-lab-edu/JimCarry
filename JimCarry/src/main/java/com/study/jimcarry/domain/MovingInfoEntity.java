package com.study.jimcarry.domain;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 이사정보 테이블
 */
@Data
public class MovingInfoEntity {
	
	// 견적요청_ID
	private String reqQuotationId;

	// 견적확정일시
	private LocalDateTime confirmQuotationDt;

	// 고객 아이디
	private String customerId;
	
	// 기사님 아이디
	private String driverId;
	
	//이사 상태
	private String movingState;
	
	// 생성자
	private String ctr;
}
