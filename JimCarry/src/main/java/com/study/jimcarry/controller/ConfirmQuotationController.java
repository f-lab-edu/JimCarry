package com.study.jimcarry.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.jimcarry.api.ConfirmQuotationResponse;
import com.study.jimcarry.api.ConfirmQuotationRequest;
import com.study.jimcarry.api.ReqQuotaionResponse;
import com.study.jimcarry.domain.ConfirmQuotationEntity;
import com.study.jimcarry.domain.ReqQuotationEntity;
import com.study.jimcarry.model.ConfirmQuotation;
import com.study.jimcarry.service.ConfirmQuotationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name="ConfirmQuotation", description="ConfirmQuotation API") //OpenAPI/Swagger에서 API를 그룹화하는데 사용
@RestController//해당 클래스가 웹 어플리케이션의 컨트롤러 임을 나타냄.
@RequestMapping(value = "/api/confirm-quotation")//해당 Controller의 URL을 지정
public class ConfirmQuotationController {
	
    private final Validator validator;
    private final ConfirmQuotationService confirmQuotationService;
    private final ModelMapper modelMapper;
    
    // 생성자 주입 방식
    public ConfirmQuotationController(Validator validator, ConfirmQuotationService confirmQuotationService, ModelMapper modelMapper) {
        this.validator = validator;
        this.confirmQuotationService = confirmQuotationService;
        this.modelMapper = modelMapper;
    }
    
	/**
	 * 견적 확정 정보 저장
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @ResponseBody //응답 결과를 응답 몸체에 직접 작성함. 메소드의 return타입에 따라 String, Json으로 반환 됨
    @PostMapping(value = "") //행위(method)는 URL에 포함하지 않는다.
    @Tag(name="ConfirmQuotation")
    @Operation(summary = "Insert ConfirmQuotation", description="견적확정 정보 저장")//OpenAPI/Swagger 사양에서 요약, 설명, 매개변수, 응답 코드 등과 같은 특정 API 엔드포인트에 대한 메타데이터를 제공하는 데 사용
	public ResponseEntity<ConfirmQuotationResponse> saveConfirmQuotation(@RequestBody @Valid ConfirmQuotationRequest reqeust) {
        return ResponseEntity.ok(
            modelMapper.map(
            		confirmQuotationService.saveConfirmQuotation(modelMapper.map(reqeust, ConfirmQuotationEntity.class)),
            		ConfirmQuotationResponse.class
            )
        );
	}
    
    /**
     * 견적 확정 정보 수정
     * @param reqeust
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PutMapping(value = "/{quotationid}") //PUT방식은 전체의 리소스를 교체 할 때 사용하고, PATCH는 리소스의 일부분을 교체 할 때 사용.
    @Tag(name="ConfirmQuotation")
    @Operation(summary = "Modify ConfirmQuotation", description="견적확정 정보 수정")
	public ResponseEntity<ConfirmQuotationResponse> modifyConfirmQuotation(
			@PathVariable("quotationid") String quotationId,
			@RequestBody @Valid ConfirmQuotationRequest reqeust) {
       	ConfirmQuotationResponse response = new ConfirmQuotationResponse();
    	ConfirmQuotationEntity confirmQuotationEntity = modelMapper.map(reqeust, ConfirmQuotationEntity.class);
    	confirmQuotationEntity.setReqQuotationId(quotationId);
    	confirmQuotationService.modifyConfrimQuotation(confirmQuotationEntity);
		return new ResponseEntity<ConfirmQuotationResponse>(response, HttpStatus.OK);
	}
    
    /**
     * 견적요청서 삭제
     * @param quotationid
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @DeleteMapping(value = "/{quotationid}")
    @Tag(name="ConfirmQuotation")
    @Operation(summary = "Delete ConfirmQuotation", description="견적 확정정보 삭제(철회)")
    public ResponseEntity<ConfirmQuotationResponse> deleteReqQuotation(@PathVariable("quotationid") String quotationId) {
       	ConfirmQuotationResponse response = new ConfirmQuotationResponse();
    	confirmQuotationService.deleteConfirmQuotation(quotationId);
		return new ResponseEntity<ConfirmQuotationResponse>(response, HttpStatus.OK);
    }
    
    /**
     * 기사님별 견적확정 정보 조회
     * @param customerId
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @GetMapping(value = "/drivers/{driverid}")
    @Tag(name="ConfirmQuotation")
    @Operation(summary = "get ConfirmQuotationList", description="기사님별 견적확정 정보 조회")
    public ResponseEntity<ConfirmQuotationResponse> getConfirmQuotationList(@PathVariable("driverid") String driverId) {
    	ConfirmQuotationResponse response = new ConfirmQuotationResponse();
    	List<ConfirmQuotation> ConfirmQuotationList = confirmQuotationService.getConfirmQuotationListByDriver(driverId);
    	response.setConfrimQuotationList(ConfirmQuotationList);
		return new ResponseEntity<ConfirmQuotationResponse>(response, HttpStatus.OK);
    }
    
    /**
     * 사용자별 견적확정 정보 조회
     * @param customerId
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @GetMapping(value = "/customers/{customerid}")
    @Tag(name="ConfirmQuotation")
    @Operation(summary = "get ConfirmQuotation", description="사용자별 견적 확정정보 조회")
    public ResponseEntity<ConfirmQuotationResponse> getConfirmQuotation(@PathVariable("customerid") String customerId){
    	ConfirmQuotationResponse response = new ConfirmQuotationResponse();
    	ConfirmQuotation confirmQuotation = confirmQuotationService.getConfirmQuotationByUser(customerId);
    	response.setConfrimQuotation(confirmQuotation);
		return new ResponseEntity<ConfirmQuotationResponse>(response, HttpStatus.OK);
    }
    
}
