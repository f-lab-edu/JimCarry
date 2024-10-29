package com.study.jimcarry.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.jimcarry.model.QuotationAcceptDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // null 제외
public class QuotationAcceptResponse extends CommonResponse {
	
	//이사정보
	@Schema(name="quotationAccept", description = "견적채택")
	@JsonProperty("quotationAccept")
    @Expose
    @SerializedName("quotationAccept")
	private QuotationAcceptDTO quotationAccept;
	
	//견적채택 리스트
	@Schema(name="quotationAcceptList", description = "이사정보 리스트")
	@JsonProperty("quotationAcceptList")
    @Expose
    @SerializedName("quotationAcceptList")
	private List<QuotationAcceptDTO> quotationAcceptList;
	
	@Schema(name="resultRow", description = "저장/수정 시 행 결과")
	@JsonProperty("resultRow")
    @Expose
    @SerializedName("resultRow")
	private int resultRow;
}
