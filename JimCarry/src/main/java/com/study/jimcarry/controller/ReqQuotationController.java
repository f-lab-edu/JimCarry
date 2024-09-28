package com.study.jimcarry.controller;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.jimcarry.api.ReqQuotaionResponse;
import com.study.jimcarry.api.ReqQuotationRequest;
import com.study.jimcarry.domain.ReqQuotationEntity;
import com.study.jimcarry.exception.CustomException;
import com.study.jimcarry.exception.ErrorCode;
import com.study.jimcarry.model.ReqQuotation;
import com.study.jimcarry.service.ReqQuotationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name="ReqQuotation", description="ReqQuotation API")
@Controller
@RequestMapping(value = "/api/req-quotation")
public class ReqQuotationController {
	
	@Autowired
	ReqQuotationService reqQuotationService;

	Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	private ModelMapper modelMapper = new ModelMapper();
	
	/**
	 * 견적요청서 저장
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @ResponseBody
    @PostMapping(value = "/save")
    @Tag(name="ReqQuotaion")
    @Operation(summary = "Insert ReqQuotaion", description="견적요청서 저장")
	public ResponseEntity<ReqQuotaionResponse> saveReqQuotation(@RequestBody @Valid ReqQuotationRequest reqeust, ReqQuotaionResponse response) throws Exception {
    	
    	//valid 체크 하고 싶은 필드가 있을 시 valids 배열에 기재
//		String[] valids = {"ReqQuotationId"};
//		for(String field: valids) {
//			Set<ConstraintViolation<ReqQuotationRequest>> violations = validator.validateProperty(reqeust, field);
//			if(!violations.isEmpty()) {
//				ConstraintViolation<ReqQuotationRequest> violation = violations.iterator().next();
//				throw new CustomException(ErrorCode.BAD_REQUEST.getCode(), violation.getMessage());
//			}
//		}
		
    	ReqQuotationEntity reqQuotationEntity = modelMapper.map(reqeust, ReqQuotationEntity.class);
		reqQuotationService.saveReqQuotation(reqQuotationEntity);
		return new ResponseEntity<ReqQuotaionResponse>(response, HttpStatus.OK);
	}
    
    /**
     * 견적요청서 수정
     * @param reqeust
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/modify")
    @Tag(name="ReqQuotaion")
    @Operation(summary = "Modify ReqQuotaion", description="견적요청서 수정")
	public ResponseEntity<ReqQuotaionResponse> modifyReqQuotation(@RequestBody @Valid ReqQuotationRequest reqeust, ReqQuotaionResponse response) throws Exception {
		ReqQuotationEntity reqQuotationEntity = modelMapper.map(reqeust, ReqQuotationEntity.class);
		reqQuotationService.modifyReqQuotation(reqQuotationEntity);
		return new ResponseEntity<ReqQuotaionResponse>(response, HttpStatus.OK);
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
    @Tag(name="ReqQuotaion")
    @Operation(summary = "Delete ReqQuotaion", description="견적요청서 삭제")
    public ResponseEntity<ReqQuotaionResponse> deleteReqQuotation(@PathVariable("quotationid") String quotationId, ReqQuotaionResponse response) throws Exception {
    	reqQuotationService.deleteReqQuotation(quotationId);
		return new ResponseEntity<ReqQuotaionResponse>(response, HttpStatus.OK);
    }
    
    /**
     * 견적요청서 전체 조회
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("/list")
    @Tag(name="ReqQuotaion")
    @Operation(summary = "get ReqQuotaionList", description="견적요청서 전체 조회")
    public ResponseEntity<ReqQuotaionResponse> getReqQuotaionList(ReqQuotaionResponse response) throws Exception {
    	List<ReqQuotation> reqQuotationList = reqQuotationService.getReqQuotationList();
    	response.setReqQuotationList(reqQuotationList);
    	return new ResponseEntity<ReqQuotaionResponse>(response, HttpStatus.OK);
    }
    
    /**
     * 사용자별 견적요청서 조회
     * @param customerId
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @GetMapping(value = "/customer/{customerid}")
    @Tag(name="ReqQuotaion")
    @Operation(summary = "get ReqQuotaion", description="사용자별 견적요청서 조회")
    public ResponseEntity<ReqQuotaionResponse> getReqQuotation(@PathVariable("customerid") String customerId, ReqQuotaionResponse response) throws Exception {
    	ReqQuotation reqQuotation = reqQuotationService.getReqQuotationByUser(customerId);
    	response.setReqQuotation(reqQuotation);
		return new ResponseEntity<ReqQuotaionResponse>(response, HttpStatus.OK);
    }
    
    /**
     * 견적요청서 채택 상태 갱신
     * @param reqQuotationId
     * @param isAccepted
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PatchMapping(value = "/{reqquotationid}/{isaccepted}")
    @Tag(name="ReqQuotaion")
    @Operation(summary = "update ReqQuotaion isAccepted", description="견적요청 채택 상태 갱신")
    public ResponseEntity<ReqQuotaionResponse> patchQuotationIsAccepted(@PathVariable("reqquotationid") String reqQuotationId, @PathVariable("isaccepted") Boolean isAccepted, ReqQuotaionResponse response) throws Exception {
    	reqQuotationService.updateReqQuotationIsAccepted(reqQuotationId, isAccepted);
    	return new ResponseEntity<ReqQuotaionResponse>(response, HttpStatus.OK);
    }
}
