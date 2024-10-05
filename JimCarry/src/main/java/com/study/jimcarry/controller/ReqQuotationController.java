package com.study.jimcarry.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.study.jimcarry.model.ReqQuotation;
import com.study.jimcarry.service.ReqQuotationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name="ReqQuotation", description="ReqQuotation API")
//@Controller
@RestController
@RequestMapping(value = "/api/req-quotation")
public class ReqQuotationController {
	
	@Autowired
	ReqQuotationService reqQuotationService;

	Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	//TODO Java @Bean으로 등록하기
	private ModelMapper modelMapper = new ModelMapper();
	
	/**
	 * 견적요청서 저장
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @ResponseBody
    @PostMapping(value = "")//행위(method)는 URL에 포함하지 않는다.
    @Tag(name="ReqQuotaion")
    @Operation(summary = "Insert ReqQuotaion", description="견적요청서 저장")
	public ResponseEntity<ReqQuotaionResponse> saveReqQuotation(@RequestBody @Valid ReqQuotationRequest reqeust, ReqQuotaionResponse response) {
    	
    	//TODO 메소드 시그니처 측면에서 고민해볼 내용
    	//TODO Response
    	//response으로 인자를 받는 것이 어떤 차이가 있는지 알아볼것!
    	//외부에서 인자가 어떻게 만들어지고 전달이 되는지 알아볼것!
    	
    	//TODO Exception
    	//예외 처리 과정 공부하기
    	
    	//TODO 서블릿 컨테이너... 개념공부...
    	
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
    @PutMapping(value = "/{quotationid}") //PUT방식은 전체의 리소스를 교체 할 때 사용하고, PATCH는 리소스의 일부분을 교체 할 때 사용.
    @Tag(name="ReqQuotaion")
    @Operation(summary = "Modify ReqQuotaion", description="견적요청서 수정")
	public ResponseEntity<ReqQuotaionResponse> modifyReqQuotation(@RequestBody @Valid ReqQuotationRequest reqeust, 
			@PathVariable("quotationid") String quotationId,
			ReqQuotaionResponse response) {
		ReqQuotationEntity reqQuotationEntity = modelMapper.map(reqeust, ReqQuotationEntity.class);
		reqQuotationEntity.setReqQuotationId(quotationId);
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
    public ResponseEntity<ReqQuotaionResponse> getReqQuotaionList(ReqQuotaionResponse response) {
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
    @GetMapping(value = "/customers/{customerid}")
    @Tag(name="ReqQuotaion")
    @Operation(summary = "get ReqQuotaion", description="사용자별 견적요청서 조회")
    public ResponseEntity<ReqQuotaionResponse> getReqQuotation(@PathVariable("customerid") String customerId, ReqQuotaionResponse response) {
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
    public ResponseEntity<ReqQuotaionResponse> patchQuotationIsAccepted(@PathVariable("reqquotationid") String reqQuotationId, @PathVariable("isaccepted") Boolean isAccepted, ReqQuotaionResponse response) {
    	reqQuotationService.updateReqQuotationIsAccepted(reqQuotationId, isAccepted);
    	return new ResponseEntity<ReqQuotaionResponse>(response, HttpStatus.OK);
    }
}
