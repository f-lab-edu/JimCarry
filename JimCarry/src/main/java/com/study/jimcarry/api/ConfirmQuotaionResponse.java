package com.study.jimcarry.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.jimcarry.model.ConfirmQuotation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null 제외
public class ConfirmQuotaionResponse extends CommonResponse {
	
	//견적요청서
	@Schema(name="confrimQuotation", description = "견적확정 정보")
	@JsonProperty("confrimQuotation")
    @Expose
    @SerializedName("confrimQuotation")
	ConfirmQuotation confrimQuotation;
	
	//견적확정 정보 리스트
	@Schema(name="confrimQuotationList", description = "견적확정 정보 리스트")
	@JsonProperty("confrimQuotationList")
    @Expose
    @SerializedName("confrimQuotationList")
	List<ConfirmQuotation> confrimQuotationList;
}
