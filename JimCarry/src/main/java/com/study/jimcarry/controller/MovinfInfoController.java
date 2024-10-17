package com.study.jimcarry.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.jimcarry.api.MovingInfoRequest;
import com.study.jimcarry.api.MovingInfoResponse;
import com.study.jimcarry.api.ReqQuotaionResponse;
import com.study.jimcarry.domain.MovingInfoEntity;
import com.study.jimcarry.domain.ReqQuotationEntity;
import com.study.jimcarry.model.MovingInfo;
import com.study.jimcarry.service.MovingInfoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name="MovingInfomation", description="MovingInfomation API") //OpenAPI/Swagger에서 API를 그룹화하는데 사용
@RestController
@RequestMapping(value = "/api/moving-info")//해당 Controller의 URL을 지정
public class MovinfInfoController {
	
    private final Validator validator;
    private final MovingInfoService movingInfoService;
    private final ModelMapper modelMapper;

    // 생성자 주입 방식 생성자가 1개 일때 @Autowired 생략이 가능
    public MovinfInfoController(Validator validator, MovingInfoService movingInfoService, ModelMapper modelMapper) {
        this.validator = validator;
        this.movingInfoService = movingInfoService;
        this.modelMapper = modelMapper;
    }
	
    @PostMapping(value = "") //행위(method)는 URL에 포함하지 않는다.
    @Tag(name="MovingInfomation")
    @Operation(summary = "Insert MovingInfomation", description="이사 정보 저장")//OpenAPI/Swagger 사양에서 요약, 설명, 매개변수, 응답 코드 등과 같은 특정 API 엔드포인트에 대한 메타데이터를 제공하는 데 사용
	public ResponseEntity<MovingInfoResponse> saveMovingInfomation(@RequestBody @Valid MovingInfoRequest reqeust) {
        return ResponseEntity.ok(
            modelMapper.map(
            		movingInfoService.saveMovingInfo(modelMapper.map(reqeust, MovingInfoEntity.class)),
            		MovingInfoResponse.class
            )
        );
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
    	MovingInfoResponse response = new MovingInfoResponse();
    	List<MovingInfo> movingInfoList = movingInfoService.getMovingInfoList();
    	response.setMovingInfoList(movingInfoList);
    	return new ResponseEntity<MovingInfoResponse>(response, HttpStatus.OK);
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
    	MovingInfoResponse response = new MovingInfoResponse();
    	MovingInfo movingInfo = movingInfoService.getMovingInfoByCustomers(customerId);
    	response.setMovingInfo(movingInfo);
		return new ResponseEntity<MovingInfoResponse>(response, HttpStatus.OK);
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
    	MovingInfoResponse response = new MovingInfoResponse();
    	MovingInfo movingInfo = movingInfoService.getMovingInfoByDrivers(driverId);
    	response.setMovingInfo(movingInfo);
		return new ResponseEntity<MovingInfoResponse>(response, HttpStatus.OK);
    }
}
