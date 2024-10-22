package com.study.jimcarry.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

/**
 * 견적요청 테이블
 */
@Getter
@Builder
public class ReqQuotationEntity {
	
	// 견적요청_ID
	private String reqQuotationId;

	// 견적요청일시
	private LocalDateTime reqQuotationDt;

	// 고객 아이디
	private String customerId;

	// 출발지 주소
	private String departureAddress;

	// 목적지 주소
	private String destinationAddress;

	// 이사 예정일자
	private LocalDate movingDate;

	// 건물 종류 (빌라/연립, 오피스텔, 주택, 아파트, 상가/사무실)
	private String buildingType;

	// 방 구조 (원룸, 1.5룸, 2룸, 3룸, 4룸, 5룸 이상)
	private String roomStructure;

	// 집 평수
	private double houseArea;

	// 엘리베이터 여부
	private boolean hasElevator;

	// 주차 여부
	private boolean hasParking;

	// 짐 박스 갯수
	private int boxCount;

	// 견적요청 금액
	private BigDecimal requestedEstimate;

	// 채택 여부
	private boolean isAccepted;

	public ReqQuotationEntityBuilder toBuilder() {
	    return ReqQuotationEntity.builder()
	            .reqQuotationId(this.reqQuotationId);
	}
}
