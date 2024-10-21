package com.study.jimcarry.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.jimcarry.model.ReqQuotation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // null 제외
public class ReqQuotaionResponse extends CommonResponse {
	
	//견적요청서
	@Schema(name="reqQuotationt", description = "견적요청서")
	@JsonProperty("reqQuotation")
    @Expose
    @SerializedName("reqQuotation")
	private ReqQuotation reqQuotation;
	
	//견적요청서 리스트
	@Schema(name="reqQuotationList", description = "견적요청서 리스트")
	@JsonProperty("reqQuotationList")
    @Expose
    @SerializedName("reqQuotationList")
	private List<ReqQuotation> reqQuotationList;
	
	@Schema(name="resultRow", description = "저장/수정 시 행 결과")
	@JsonProperty("resultRow")
    @Expose
    @SerializedName("resultRow")
	private int resultRow;
	
}
