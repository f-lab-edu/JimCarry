package com.study.jimcarry.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * logoutResponse
 * @author "Hong Seok Woo"
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null 제외
public class LogoutResponse extends CommonResponse {
	
	/**
	 * login URL
	 */
	@Schema(name="logOutUrl", description = "login URL")
	@JsonProperty("logOutUrl")
    @Expose
    @SerializedName("logOutUrl")
	private String logOutUrl = "/login";;
}
