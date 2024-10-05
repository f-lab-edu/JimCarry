package com.study.jimcarry.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null 제외
public class MovingInfo {
	
	// 견적요청_ID
	@Schema(name = "reqQuotationId", description = "견적요청_ID")
	@JsonProperty("reqQuotationId")
	@Expose
	@SerializedName("reqQuotationId")
	private String reqQuotationId;

	// 견적확정일시
	@Schema(name = "confirmQuotationDt", description = "견적확정일시")
	@JsonProperty("confirmQuotationDt")
	@Expose
	@SerializedName("confirmQuotationDt")
	private LocalDateTime confirmQuotationDt;

	// 고객 아이디
	@Schema(name = "customerId", description = "고객 아이디")
	@JsonProperty("customerId")
	@Expose
	@SerializedName("customerId")
	private String customerId;

	// 기사님 아이디
	@Schema(name = "driverId", description = "기사님 아이디")
	@JsonProperty("driverId")
	@Expose
	@SerializedName("driverId")
	private String driverId;
	
	// 이사상태
	@Schema(name = "movingState", description = "이사 상태")
	@JsonProperty("movingState")
	@Expose
	@SerializedName("movingState")
	private String movingState;
}
