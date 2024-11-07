package com.study.jimcarry.api;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReqQuotationRequest {
	
	//견적요청일시
//	@JsonProperty("quotationDt") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
//	@Schema(name="quotationDt", description="견적요청일시") //Swagger/OpenAPI 문서를 자동 생성
//	@NotNull(message="견적 요청 일시는 필수입니다.")
//	private LocalDateTime quotationDt;
	
	//고객 아이디
	@JsonProperty("custId") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="custId", description="고객 아이디") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="고객 ID는 필수입니다.")
	private String custId;
	
    // 출발지 주소
	@JsonProperty("pickupAddr") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="pickupAddr", description="출발지 주소") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="출발지 주소는 필수입니다.")
    private String pickupAddr;

    // 목적지 주소
	@JsonProperty("deliveryAddr") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="deliveryAddr", description="목적지 주소") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="목적지 주소는 필수입니다.")
    private String deliveryAddr;

    // 이사 예정일자
	@JsonProperty("moveDt") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="moveDt", description="이사 일자") //Swagger/OpenAPI 문서를 자동 생성
	@NotNull(message="이사 일자는 필수입니다.")
    private Date moveDt;

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
	@JsonProperty("houseSize") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="houseSize", description="집 평수") //Swagger/OpenAPI 문서를 자동 생성
	@NotNull(message="집 평수는 필수입니다.")
    private int houseSize;

    // 엘리베이터 여부
	@JsonProperty("hasElevator") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="hasElevator", description="엘리베이터 여부") //Swagger/OpenAPI 문서를 자동 생성
    private boolean hasElevator;

    // 짐 박스 갯수
	@JsonProperty("boxCount") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="boxCount", description="짐 박스 갯수") //Swagger/OpenAPI 문서를 자동 생성
    private int boxCount;

    // 견적요청 금액
	@JsonProperty("quotationAmount") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="quotationAmount", description="견적요청 금액") //Swagger/OpenAPI 문서를 자동 생성
	@NotNull(message="견적 요청 금액은 필수입니다.")
	@DecimalMin(value = "0.0", message="견적 요청 금액은 0.0 이상이어야 합니다.")
    private BigDecimal quotationAmount;

    // 생성자
	@JsonProperty("cid") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="cid", description="생성자") //Swagger/OpenAPI 문서를 자동 생성
	@NotNull(message="생성자는 필수입니다.")
    private Integer cid;
	
	//이사 짐 정보 리스트
	@JsonProperty("moveItemList") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="moveItemList", description="이사 짐 정보 리스트") //Swagger/OpenAPI 문서를 자동 생성
	@NotNull(message="이사 짐 정보 리스트는 필수입니다.")
	private List<MoveItemRequest> moveItemList;
	
	@JsonProperty("status") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="status", description="견적상태") //Swagger/OpenAPI 문서를 자동 생성
	private String status;
}
