package com.study.jimcarry.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
	
	@JsonProperty("userId") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="userId", description="사용자 아이디") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="사용자 아이디는 필수입니다.")
	private String userId;
	
	@JsonProperty("userType") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="userType", description="유저 타입") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="사용자 유형은 필수입니다.")
	private String userType; // admin/user/driver
	
	@JsonProperty("userName") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="userName", description="사용자 이름") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="사용자 이름은 필수입니다.")
	private String userName;
	
	@JsonProperty("password") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="password", description="암호") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="암호는 필수입니다.")
	private String password;
	
	@JsonProperty("curPassword") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="curPassword", description="현재 암호") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="현재암호는 필수입니다.")
	private String curPassword;
	
	@JsonProperty("phoneNumber") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="phoneNumber", description="전화번호") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="전화번호는 필수입니다.")
	private String phoneNumber;
	
	@JsonProperty("email") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="email", description="이메일") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="이메일은 필수입니다.")
	private String email;
	
}
