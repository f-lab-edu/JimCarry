package com.study.jimcarry.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.jimcarry.api.MovingInfoRequest;
import com.study.jimcarry.api.MovingInfoResponse;
import com.study.jimcarry.exception.CustomException;
import com.study.jimcarry.exception.ErrorCode;
import com.study.jimcarry.model.MovingInfo;
import com.study.jimcarry.service.MovingInfoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name="MovingInfomation", description="MovingInfomation API") //OpenAPI/Swagger에서 API를 그룹화하는데 사용
@RestController
@RequestMapping(value = "/api/moving-info")//해당 Controller의 URL을 지정
@RequiredArgsConstructor
public class MovingInfoController {
	
//    private final Validator validator;
    private final MovingInfoService movingInfoService;
//    private final ModelMapper modelMapper;

    // 생성자 주입 방식 생성자가 1개 일때 @Autowired 생략이 가능
//    public MovinfInfoController(Validator validator, MovingInfoService movingInfoService, ModelMapper modelMapper) {
//        this.validator = validator;
//        this.movingInfoService = movingInfoService;
//        this.modelMapper = modelMapper;
//    }
	
    @PostMapping(value = "") //행위(method)는 URL에 포함하지 않는다.
    @Tag(name="MovingInfomation")
    @Operation(summary = "Insert MovingInfomation", description="이사 정보 저장")//OpenAPI/Swagger 사양에서 요약, 설명, 매개변수, 응답 코드 등과 같은 특정 API 엔드포인트에 대한 메타데이터를 제공하는 데 사용
	public ResponseEntity<MovingInfoResponse> saveMovingInfomation(@RequestBody @Valid MovingInfoRequest request, BindingResult bindingResult) {
    	
    	//3.Spring 컨텍스트 내에서 유효성 검증을 지원합니다. BindingResult 방식으로 개선하세요.
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                throw new CustomException(ErrorCode.BAD_REQUEST.getCode(), fieldError.getDefaultMessage());
            }
        }
        
		MovingInfo movingInfo = MovingInfo.builder()
				.reqQuotationId(request.getReqQuotationId())
				.acceptQuotationDt(request.getAcceptQuotationDt())
				.customerId(request.getCustomerId())
				.driverId(request.getDriverId())
				.movingState(request.getMovingState())
				.build();
		
		MovingInfoResponse response = MovingInfoResponse.builder()
				.resultRow(movingInfoService.saveMovingInfo(movingInfo))
				.build();
        return ResponseEntity.ok(response);
	}
    
    /**
     * 이사정보 전체 조회
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    @Tag(name="MovingInfomation")
    @Operation(summary = "get MovingInfomationList", description="이사정보 전체 조회")
    public ResponseEntity<MovingInfoResponse> getReqQuotaionList() {
    	List<MovingInfo> movingInfoList = movingInfoService.getMovingInfoList();
		MovingInfoResponse response = MovingInfoResponse.builder()
				.movingInfoList(movingInfoList)
				.build();
        return ResponseEntity.ok(response);
    }
    
    /**
     * 사용자별 이사정보 조회
     * @param customerId
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/customers/{customerid}")
    @Tag(name="MovingInfomation")
    @Operation(summary = "get MovingInfomation", description="사용자별 이사정보 조회")
    public ResponseEntity<MovingInfoResponse> getMovingInfoBycustomer(@PathVariable("customerid") String customerId) {
    	MovingInfo movingInfo = movingInfoService.getMovingInfoByCustomers(customerId);
		MovingInfoResponse response = MovingInfoResponse.builder()
				.movingInfo(movingInfo)
				.build();
        return ResponseEntity.ok(response);
    }
    
    /**
     * 기사님별 이사정보 조회
     * @param driversId
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/drivers/{driverid}")
    @Tag(name="MovingInfomation")
    @Operation(summary = "get MovingInfomation", description="기사님별 이사정보 조회")
    public ResponseEntity<MovingInfoResponse> getMovingInfoBydriver(@PathVariable("driverid") String driverId) {
    	MovingInfo movingInfo = movingInfoService.getMovingInfoByDrivers(driverId);
		MovingInfoResponse response = MovingInfoResponse.builder()
				.movingInfo(movingInfo)
				.build();
        return ResponseEntity.ok(response);
    }
}
