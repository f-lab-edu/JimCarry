package com.study.jimcarry.controller;

import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.jimcarry.api.UserRequest;
import com.study.jimcarry.api.UserResponse;
import com.study.jimcarry.domain.UserEntity;
import com.study.jimcarry.exception.CustomException;
import com.study.jimcarry.exception.ErrorCode;
import com.study.jimcarry.model.UserInfoDTO;
import com.study.jimcarry.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Auth", description = "Auth API") // OpenAPI/Swagger에서 API를 그룹화하는데 사용
@RestController // 해당 클래스가 웹 어플리케이션의 컨트롤러 임을 나타냄.
@RequestMapping(value = "/api/auth") // 해당 Controller의 URL을 지정
@RequiredArgsConstructor
public class UserController {

	private final Validator validator;
	private final UserService userService;

//	public AuthController(Validator validator, AuthService authService, ModelMapper modelMapper) {
//		this.validator = validator;
//		this.authService = authService;
//		this.modelMapper = modelMapper;
//	}

	@PostMapping(value = "/users") // 행위(method)는 URL에 포함하지 않는다.
	@Tag(name = "Auth")
	@Operation(summary = "Insert userInfo", description = "사용자 정보 저장") // OpenAPI/Swagger 사양에서 요약, 설명, 매개변수, 응답 코드 등과 같은 특정 API 엔드포인트에 대한 메타데이터를 제공하는 데 사용
	public ResponseEntity<UserResponse> saveUsers(@RequestBody UserRequest request) {

		log.debug("Received request: {}", request);
		String[] valids = { "userId", "userName", "email", "password", "userType", "phoneNumber" };
		for (String field : valids) {
			Set<ConstraintViolation<UserRequest>> violations = validator.validateProperty(request, field);
			if (!violations.isEmpty()) {
				ConstraintViolation<UserRequest> violation = violations.iterator().next();
				throw new CustomException(ErrorCode.NOT_FOUND.getCode(), violation.getMessage());
			}
		}

		UserInfoDTO userInfo = UserInfoDTO.builder().userId(request.getUserId()).userName(request.getUserName())
				.password(request.getPassword()).phoneNumber(request.getPhoneNumber()).userType(request.getUserType())
				.email(request.getEmail()).build();

		UserResponse userResponse = UserResponse.builder().resultRow(userService.saveUserInfo(userInfo)).build();

		return ResponseEntity.ok(userResponse);
	}

	@GetMapping(value = "/users")
	@Tag(name = "Auth")
	@Operation(summary = "Find UserId", description = "사용자 아이디 찾기") // OpenAPI/Swagger 사양에서 요약, 설명, 매개변수, 응답 코드 등과 같은 특정
																	// API 엔드포인트에 대한 메타데이터를 제공하는 데 사용
	public ResponseEntity<UserResponse> findUserId(@RequestBody UserRequest request) {
		log.debug("Received request: {}", request);
		String[] valids = { "userName", "email" };
		for (String field : valids) {
			Set<ConstraintViolation<UserRequest>> violations = validator.validateProperty(request, field);
			if (!violations.isEmpty()) {
				ConstraintViolation<UserRequest> violation = violations.iterator().next();
				throw new CustomException(ErrorCode.NOT_FOUND.getCode(), violation.getMessage());
			}
		}

		UserInfoDTO userInfo = UserInfoDTO.builder().userName(request.getUserName()).email(request.getEmail()).build();

		UserResponse userResponse = UserResponse.builder().userId(userService.findUserId(userInfo)).build();

		return ResponseEntity.ok(userResponse);
	}

	@PatchMapping(value = "/users")
	@Tag(name = "Auth")
	@Operation(summary = "Change Password", description = "사용자 암호 변경") // OpenAPI/Swagger 사양에서 요약, 설명, 매개변수, 응답 코드 등과 같은
																		// 특정 API 엔드포인트에 대한 메타데이터를 제공하는 데 사용
	public ResponseEntity<UserResponse> changePassword(@RequestBody UserRequest request) {
		log.debug("Received request: {}", request);
		String[] valids = { "userId", "password", "curPassword" };
		for (String field : valids) {
			Set<ConstraintViolation<UserRequest>> violations = validator.validateProperty(request, field);
			if (!violations.isEmpty()) {
				ConstraintViolation<UserRequest> violation = violations.iterator().next();
				throw new CustomException(ErrorCode.NOT_FOUND.getCode(), violation.getMessage());
			}
		}

		UserInfoDTO userInfo = UserInfoDTO.builder().userId(request.getUserId()).password(request.getPassword()).build();

		UserResponse userResponse = UserResponse.builder().resultRow(userService.changePassword(userInfo)).build();

		return ResponseEntity.ok(userResponse);
	}
}
