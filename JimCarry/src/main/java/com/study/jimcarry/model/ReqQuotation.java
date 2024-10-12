package com.study.jimcarry.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null 제외
public class ReqQuotation {
	
	// 견적요청_ID
	@Schema(name = "reqQuotationId", description = "견적요청_ID")
	@JsonProperty("reqQuotationId")
	@Expose
	@SerializedName("reqQuotationId")
	private String reqQuotationId;

	// 견적요청일시
	@Schema(name = "reqQuotationDt", description = "견적요청일시")
	@JsonProperty("reqQuotationDt")
	@Expose
	@SerializedName("reqQuotationDt")
	private LocalDateTime reqQuotationDt;

	// 고객 아이디
	@Schema(name = "customerId", description = "고객 아이디")
	@JsonProperty("customerId")
	@Expose
	@SerializedName("customerId")
	private String customerId;

	// 출발지 주소
	@Schema(name = "departureAddress", description = "출발지 주소")
	@JsonProperty("departureAddress")
	@Expose
	@SerializedName("departureAddress")
	private String departureAddress;

	// 목적지 주소
	@Schema(name = "destinationAddress", description = "목적지 주소")
	@JsonProperty("destinationAddress")
	@Expose
	@SerializedName("destinationAddress")
	private String destinationAddress;

	// 이사 예정일자
	@Schema(name = "movingDate", description = "이사 예정일자")
	@JsonProperty("movingDate")
	@Expose
	@SerializedName("movingDate")
	private LocalDate movingDate;

	// 건물 종류 (빌라/연립, 오피스텔, 주택, 아파트, 상가/사무실)
	@Schema(name = "buildingType", description = "건물 종류 (빌라/연립, 오피스텔, 주택, 아파트, 상가/사무실)")
	@JsonProperty("buildingType")
	@Expose
	@SerializedName("buildingType")
	private String buildingType;

	// 방 구조 (원룸, 1.5룸, 2룸, 3룸, 4룸, 5룸 이상)
	@Schema(name = "roomStructure", description = "방 구조 (원룸, 1.5룸, 2룸, 3룸, 4룸, 5룸 이상)")
	@JsonProperty("roomStructure")
	@Expose
	@SerializedName("roomStructure")
	private String roomStructure;

	// 집 평수
	@Schema(name = "houseArea", description = "집 평수")
	@JsonProperty("houseArea")
	@Expose
	@SerializedName("houseArea")
	private double houseArea;

	// 엘리베이터 여부
	@Schema(name = "hasElevator", description = "엘리베이터 여부")
	@JsonProperty("hasElevator")
	@Expose
	@SerializedName("hasElevator")
	private boolean hasElevator;

	// 주차 여부
	@Schema(name = "hasParking", description = "주차 여부")
	@JsonProperty("hasParking")
	@Expose
	@SerializedName("hasParking")
	private boolean hasParking;

	// 짐 박스 갯수
	@Schema(name = "boxCount", description = "짐 박스 갯수")
	@JsonProperty("boxCount")
	@Expose
	@SerializedName("boxCount")
	private int boxCount;

	// 견적요청 금액
	@Schema(name = "requestedEstimate", description = "견적요청 금액")
	@JsonProperty("requestedEstimate")
	@Expose
	@SerializedName("requestedEstimate")
	private BigDecimal requestedEstimate;

	// 채택 여부
	@Schema(name = "isAccepted", description = "채택 여부")
	@JsonProperty("isAccepted")
	@Expose
	@SerializedName("isAccepted")
	private boolean isAccepted;

}
