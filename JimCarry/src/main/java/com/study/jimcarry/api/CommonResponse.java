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
	@Schema(name="resultCode", description = "응답코드") //Swagger/OpenAPI 주석 의 일부이며 API 모델을 설명하는 데 사용
	@JsonProperty("resultCode") //Jackson 라이브러리에서 사용 하는 것으로 Json에서 Java객체를 직렬화/역직렬화 할때 사용
    @Expose //Gson 라이브러리로 Java에서 Json을 처리 하기 위해 사용
    @SerializedName("resultCode") //Gson 라이브러리로 객체가 직렬화 되거나 역직렬화 할 때 사용
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
	
	//TEST CI
}
