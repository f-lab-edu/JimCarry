package com.study.jimcarry.controller;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name="ReqQuotation", description="ReqQuotation API")
@RestController
@RequestMapping(value = "/api/req-quotation")
public class ReqQuotationController {
	
    private final Validator validator;
    private final ReqQuotationService reqQuotationService;
    private final ModelMapper modelMapper;

    // 생성자 주입 방식
    public ReqQuotationController(Validator validator, ReqQuotationService reqQuotationService, ModelMapper modelMapper) {
        this.validator = validator;
        this.reqQuotationService = reqQuotationService;
        this.modelMapper = modelMapper;
    }
    
	/**
	 * 견적요청서 저장
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @PostMapping
    @Tag(name="ReqQuotaion")
    @Operation(summary = "Insert ReqQuotaion", description="견적요청서 저장")
	public ResponseEntity<ReqQuotaionResponse> saveReqQuotation(@RequestBody @Valid ReqQuotationRequest reqeust) {
        return ResponseEntity.ok(
            modelMapper.map(
                reqQuotationService.saveReqQuotation(modelMapper.map(reqeust, ReqQuotationEntity.class)),
                ReqQuotaionResponse.class
            )
        );
	}
    
    /**
     * 견적요청서 수정
     * @param reqeust
     * @param response
     * @return
     * @throws Exception
     */
    @PutMapping(value = "/{quotationid}") //PUT방식은 전체의 리소스를 교체 할 때 사용하고, PATCH는 리소스의 일부분을 교체 할 때 사용.
    @Tag(name="ReqQuotaion")
    @Operation(summary = "Modify ReqQuotaion", description="견적요청서 수정")
	public ResponseEntity<ReqQuotaionResponse> modifyReqQuotation(@RequestBody @Valid ReqQuotationRequest request, 
			@PathVariable("quotationid") String quotationId) {
    		reqQuotationService.modifyReqQuotation(modelMapper.map(request, ReqQuotationEntity.class)
    		    .toBuilder()
    		    .reqQuotationId(quotationId)
    		    .build()
    		);
		return ResponseEntity.ok(new ReqQuotaionResponse());
	}
    
    /**
     * 견적요청서 삭제
     * @param quotationid
     * @param response
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "/{quotationid}")
    @Tag(name="ReqQuotaion")
    @Operation(summary = "Delete ReqQuotaion", description="견적요청서 삭제")
    public ResponseEntity<ReqQuotaionResponse> deleteReqQuotation(@PathVariable("quotationid") String quotationId) {
    	ReqQuotaionResponse response = new ReqQuotaionResponse();
    	reqQuotationService.deleteReqQuotation(quotationId);
		return new ResponseEntity<ReqQuotaionResponse>(response, HttpStatus.OK);
    }
    
    /**
     * 견적요청서 전체 조회
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    @Tag(name="ReqQuotaion")
    @Operation(summary = "get ReqQuotaionList", description="견적요청서 전체 조회")
    public ResponseEntity<ReqQuotaionResponse> getReqQuotaionList() {
    	ReqQuotaionResponse response = new ReqQuotaionResponse();
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
    @GetMapping(value = "/customers/{customerid}")
    @Tag(name="ReqQuotaion")
    @Operation(summary = "get ReqQuotaion", description="사용자별 견적요청서 조회")
    public ResponseEntity<ReqQuotaionResponse> getReqQuotation(@PathVariable("customerid") String customerId) {
    	ReqQuotaionResponse response = new ReqQuotaionResponse();
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
    //@PatchMapping(value = "/{reqquotationid}/{isaccepted}")
    @PatchMapping
    @Tag(name="ReqQuotaion")
    @Operation(summary = "update ReqQuotaion isAccepted", description="견적요청 채택 상태 갱신")
    public ResponseEntity<ReqQuotaionResponse> patchQuotationIsAccepted(@RequestBody @Valid ReqQuotationRequest reqeust) {
    	ReqQuotaionResponse response = new ReqQuotaionResponse();
    	reqQuotationService.updateReqQuotationIsAccepted(reqeust.getReqQuotationId(), reqeust.isAccepted());
    	return new ResponseEntity<ReqQuotaionResponse>(response, HttpStatus.OK);
    }
}
