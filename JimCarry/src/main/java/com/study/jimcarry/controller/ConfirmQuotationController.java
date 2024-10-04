package com.study.jimcarry.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.jimcarry.api.ConfirmQuotaionResponse;
import com.study.jimcarry.api.ConfirmQuotationRequest;
import com.study.jimcarry.domain.ConfirmQuotationEntity;
import com.study.jimcarry.model.ConfirmQuotation;
import com.study.jimcarry.service.ConfirmQuotationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name="ConfirmQuotation", description="ConfirmQuotation API")
@Controller
@RequestMapping(value = "/api/confirm-quotation")
public class ConfirmQuotationController {
	
	@Autowired
	ConfirmQuotationService confirmQuotationService;

	Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	private ModelMapper modelMapper = new ModelMapper();

	/**
	 * 견적 확정 정보 저장
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @ResponseBody
    @PostMapping(value = "") //행위(method)는 URL에 포함하지 않는다.
    @Tag(name="ConfirmQuotation")
    @Operation(summary = "Insert ConfirmQuotation", description="견적확정 정보 저장")
	public ResponseEntity<ConfirmQuotaionResponse> saveConfirmQuotation(@RequestBody @Valid ConfirmQuotationRequest reqeust, ConfirmQuotaionResponse response) throws Exception {
    	ConfirmQuotationEntity confirmQuotationEntity = modelMapper.map(reqeust, ConfirmQuotationEntity.class);
    	confirmQuotationService.saveConfirmQuotation(confirmQuotationEntity);
		return new ResponseEntity<ConfirmQuotaionResponse>(response, HttpStatus.OK);
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
	public ResponseEntity<ConfirmQuotaionResponse> modifyConfirmQuotation(
			@PathVariable("quotationid") String quotationId,
			@RequestBody @Valid ConfirmQuotationRequest reqeust, 
			ConfirmQuotaionResponse response) throws Exception {
    	ConfirmQuotationEntity confirmQuotationEntity = modelMapper.map(reqeust, ConfirmQuotationEntity.class);
    	confirmQuotationEntity.setReqQuotationId(quotationId);
    	confirmQuotationService.modifyConfrimQuotation(confirmQuotationEntity);
		return new ResponseEntity<ConfirmQuotaionResponse>(response, HttpStatus.OK);
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
    public ResponseEntity<ConfirmQuotaionResponse> deleteReqQuotation(@PathVariable("quotationid") String quotationId, ConfirmQuotaionResponse response) throws Exception {
    	confirmQuotationService.deleteConfirmQuotation(quotationId);
		return new ResponseEntity<ConfirmQuotaionResponse>(response, HttpStatus.OK);
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
    public ResponseEntity<ConfirmQuotaionResponse> getConfirmQuotationList(@PathVariable("driverid") String driverId, ConfirmQuotaionResponse response) throws Exception {
    	List<ConfirmQuotation> ConfirmQuotationList = confirmQuotationService.getConfirmQuotationListByDriver(driverId);
    	response.setConfrimQuotationList(ConfirmQuotationList);
		return new ResponseEntity<ConfirmQuotaionResponse>(response, HttpStatus.OK);
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
    public ResponseEntity<ConfirmQuotaionResponse> getConfirmQuotation(@PathVariable("customerid") String customerId, ConfirmQuotaionResponse response) throws Exception {
    	ConfirmQuotation confirmQuotation = confirmQuotationService.getConfirmQuotationByUser(customerId);
    	response.setConfrimQuotation(confirmQuotation);
		return new ResponseEntity<ConfirmQuotaionResponse>(response, HttpStatus.OK);
    }
    
}
