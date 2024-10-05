package com.study.jimcarry.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.jimcarry.api.ConfirmQuotaionResponse;
import com.study.jimcarry.api.MovingInfoRequest;
import com.study.jimcarry.api.MovingInfoResponse;
import com.study.jimcarry.domain.MovingInfoEntity;
import com.study.jimcarry.service.MovingInfoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name="MovingInfomation", description="MovingInfomation API") //OpenAPI/Swagger에서 API를 그룹화하는데 사용
@Controller//해당 클래스가 웹 어플리케이션의 컨트롤러 임을 나타냄.
@RequestMapping(value = "/api/moving-info")//해당 Controller의 URL을 지정
public class MovinfInfoController {
	
	@Autowired
	private MovingInfoService movingInfoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
    @ResponseBody //응답 결과를 응답 몸체에 직접 작성함. 메소드의 return타입에 따라 String, Json으로 반환 됨
    @PostMapping(value = "") //행위(method)는 URL에 포함하지 않는다.
    @Tag(name="MovingInfomation")
    @Operation(summary = "Insert MovingInfomation", description="이사 정보 저장")//OpenAPI/Swagger 사양에서 요약, 설명, 매개변수, 응답 코드 등과 같은 특정 API 엔드포인트에 대한 메타데이터를 제공하는 데 사용
	public ResponseEntity<MovingInfoResponse> saveMovingInfomation(@RequestBody @Valid MovingInfoRequest reqeust) {
    	MovingInfoResponse response = new MovingInfoResponse();
    	MovingInfoEntity movingInfoEntity = modelMapper.map(reqeust, MovingInfoEntity.class);
    	movingInfoService.saveMovingInfo(movingInfoEntity);
		return new ResponseEntity<MovingInfoResponse>(response, HttpStatus.OK);
	}
}
