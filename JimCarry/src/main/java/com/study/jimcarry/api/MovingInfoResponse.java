package com.study.jimcarry.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.jimcarry.model.MovingInfo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // null 제외
public class MovingInfoResponse extends CommonResponse {
	
	//이사정보
	@Schema(name="movingInfo", description = "이사정보")
	@JsonProperty("movingInfo")
    @Expose
    @SerializedName("movingInfo")
	private MovingInfo movingInfo;
	
	//이사정보 리스트
	@Schema(name="movingInfoList", description = "이사정보 리스트")
	@JsonProperty("movingInfoList")
    @Expose
    @SerializedName("movingInfoList")
	private List<MovingInfo> movingInfoList;
	
	@Schema(name="resultRow", description = "저장/수정 시 행 결과")
	@JsonProperty("resultRow")
    @Expose
    @SerializedName("resultRow")
	private int resultRow;
}
