package com.study.jimcarry.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // null 제외
public class UserResponse extends CommonResponse {
	
	@Schema(name="resultRow", description = "저장/수정 시 행 결과")
	@JsonProperty("resultRow")
    @Expose
    @SerializedName("resultRow")
	private int resultRow;
	
	@Schema(name="userId", description = "사용자 아이디")
	@JsonProperty("userId")
    @Expose
    @SerializedName("userId")
	private String userId;
	
	
}
