package com.study.jimcarry.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * 이사정보 테이블
 */
@Getter
@Builder
public class MovingInfoEntity {
	
	// 견적요청_ID
	private String reqQuotationId;

	// 견적채택일시
	private LocalDateTime acceptQuotationDt;

	// 고객 아이디
	private String customerId;
	
	// 기사님 아이디
	private String driverId;
	
	//이사 상태
	private String movingState;
	
	// 생성자
	private String ctr;
}
