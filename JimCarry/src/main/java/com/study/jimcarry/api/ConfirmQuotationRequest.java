package com.study.jimcarry.api;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConfirmQuotationRequest {
	
	//견적요청_ID
	@JsonProperty("reqQuotationId") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="reqQuotationId", description="견적요청_ID") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="견적 요청 ID는 필수입니다.")
	private String reqQuotationId;
	
	//견적확정일시
	@JsonProperty("confirmQuotationDt") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="confirmQuotationDt", description="견적확정일시") //Swagger/OpenAPI 문서를 자동 생성
	@NotNull(message="견적 요청 일시는 필수입니다.")
	private LocalDateTime confirmQuotationDt;
	
	//고객 아이디
	@JsonProperty("customerId") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="customerId", description="고객 아이디") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="고객 ID는 필수입니다.")
	private String customerId;
	
	//기사님 아이디
	@JsonProperty("driverId") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="driverId", description="기사님 아이디") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="기사님 ID는 필수입니다.")
	private String driverId;

    // 생성자
	@JsonProperty("ctr") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="ctr", description="생성자") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="생성자는 필수입니다.")
    private String ctr;
	
}
