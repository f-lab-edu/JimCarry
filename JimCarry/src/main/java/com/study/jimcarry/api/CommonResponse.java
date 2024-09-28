package com.study.jimcarry.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.jimcarry.exception.ErrorCode;

import io.swagger.v3.oas.annotations.media.Schema;

public class CommonResponse {
	public CommonResponse() {
	}

	public CommonResponse(int resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	/**
	 * 응답코드
	 */
	@Schema(name="resultCode", description = "응답코드")
	@JsonProperty("resultCode")
    @Expose
    @SerializedName("resultCode")
	private int resultCode = 0;
	
	/**
	 * 응답메시지
	 */
	@Schema(name="resultMsg", description = "응답메시지")
	@JsonProperty("resultMsg")
    @Expose
    @SerializedName("resultMsg")
	private String resultMsg = "SUCCESS";
	
	/**
	 * 오류 설정
	 * @param err
	 */
	public void setError(ErrorCode err) {
		this.resultCode = err.getCode();
		this.resultMsg = err.getMessage();
	}
}
