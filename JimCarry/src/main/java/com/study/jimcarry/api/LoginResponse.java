package com.study.jimcarry.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.jimcarry.model.UserInfo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null 제외
public class LoginResponse extends CommonResponse {
	
	/**
	 * 유저 정보
	 */
	@Schema(name="userInfo", description = "유저 정보")
	@JsonProperty("userInfo")
    @Expose
    @SerializedName("userInfo")
	UserInfo userInfo;

	public LoginResponse() {}
	
	public LoginResponse(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
		
}
