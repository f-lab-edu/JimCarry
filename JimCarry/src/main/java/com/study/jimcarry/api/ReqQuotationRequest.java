package com.study.jimcarry.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReqQuotationRequest {
	
	//견적요청_ID
	@JsonProperty("ReqQuotationId") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="ReqQuotationId", description="견적요청_ID") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="견적 요청 ID는 필수입니다.")
	private String ReqQuotationId;
	
	//견적요청일시
	@JsonProperty("ReqQuotationDt") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="ReqQuotationDt", description="견적요청일시") //Swagger/OpenAPI 문서를 자동 생성
	@NotNull(message="견적 요청 일시는 필수입니다.")
	private LocalDateTime ReqQuotationDt;
	
	//고객 아이디
	@JsonProperty("customerId") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="customerId", description="고객 아이디") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="고객 ID는 필수입니다.")
	private String customerId;
	
    // 출발지 주소
	@JsonProperty("departureAddress") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="departureAddress", description="출발지 주소") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="출발지 주소는 필수입니다.")
    private String departureAddress;

    // 목적지 주소
	@JsonProperty("destinationAddress") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="destinationAddress", description="목적지 주소") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="목적지 주소는 필수입니다.")
    private String destinationAddress;

    // 이사 예정일자
	@JsonProperty("movingDate") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="movingDate", description="이사 예정일자") //Swagger/OpenAPI 문서를 자동 생성
	@NotNull(message="이사 예정일자는 필수입니다.")
    private LocalDate movingDate;

    // 건물 종류 (빌라/연립, 오피스텔, 주택, 아파트, 상가/사무실)
	@JsonProperty("buildingType") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="buildingType", description="건물 종류") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="건물 종류는 필수입니다.")
    private String buildingType;

    // 방 구조 (원룸, 1.5룸, 2룸, 3룸, 4룸, 5룸 이상)
	@JsonProperty("roomStructure") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="roomStructure", description="방 구조") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="방 구조는 필수입니다.")
    private String roomStructure;

    // 집 평수 (15평, 24평, 32평, 45평, 100평)
	@JsonProperty("houseArea") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="houseArea", description="집 평수") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="집 평수는 필수입니다.")
    private double houseArea;

    // 엘리베이터 여부
	@JsonProperty("hasElevator") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="hasElevator", description="엘리베이터 여부") //Swagger/OpenAPI 문서를 자동 생성
    private boolean hasElevator;

    // 주차 여부
	@JsonProperty("hasParking") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="hasParking", description="주차 여부") //Swagger/OpenAPI 문서를 자동 생성
    private boolean hasParking;

    // 짐 박스 갯수
	@JsonProperty("boxCount") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="boxCount", description="짐 박스 갯수") //Swagger/OpenAPI 문서를 자동 생성
    private int boxCount;

    // 견적요청 금액
	@JsonProperty("requestedEstimate") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="requestedEstimate", description="견적요청 금액") //Swagger/OpenAPI 문서를 자동 생성
	@NotNull(message="견적 요청 금액은 필수입니다.")
	@DecimalMin(value = "0.0", message="견적 요청 금액은 0.0 이상이어야 합니다.")
    private BigDecimal requestedEstimate;

    // 채택 여부
	@JsonProperty("isAccepted") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="isAccepted", description="채택 여부") //Swagger/OpenAPI 문서를 자동 생성
    private boolean isAccepted;

    // 생성자
	@JsonProperty("ctr") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="ctr", description="생성자") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="생성자는 필수입니다.")
    private String ctr;
	
	//TODO 이사짐 관련 필드 추가..
	//이사 짐 정보 리스트
	private List<Integer> moveItemList;
	
	//이사 짐 정보 상세리스트
	private List<Integer> moveItemDetailList;
}
