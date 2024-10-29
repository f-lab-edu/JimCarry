package com.study.jimcarry.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.jimcarry.model.ConfirmQuotationDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // null 제외
public class ConfirmQuotationResponse extends CommonResponse {
	
	//견적요청서
	@Schema(name="confrimQuotation", description = "견적확정 정보")
	@JsonProperty("confrimQuotation")
    @Expose
    @SerializedName("confrimQuotation")
	private ConfirmQuotationDTO confrimQuotation;
	
	//견적확정 정보 리스트
	@Schema(name="confrimQuotationList", description = "견적확정 정보 리스트")
	@JsonProperty("confrimQuotationList")
    @Expose
    @SerializedName("confrimQuotationList")
	private List<ConfirmQuotationDTO> confrimQuotationList;
	
	@Schema(name="resultRow", description = "저장/수정 시 행 결과")
	@JsonProperty("resultRow")
    @Expose
    @SerializedName("resultRow")
	private int resultRow;
}
