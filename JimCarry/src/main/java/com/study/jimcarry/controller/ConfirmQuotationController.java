package com.study.jimcarry.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.jimcarry.api.ConfirmQuotationRequest;
import com.study.jimcarry.api.ConfirmQuotationResponse;
import com.study.jimcarry.model.ConfirmQuotationDTO;
import com.study.jimcarry.service.ConfirmQuotationService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name="ConfirmQuotation", description="ConfirmQuotation API") //OpenAPI/Swagger에서 API를 그룹화하는데 사용
@RestController//해당 클래스가 웹 어플리케이션의 컨트롤러 임을 나타냄.
@RequestMapping(value = "/api/confirm-quotation")//해당 Controller의 URL을 지정
@RequiredArgsConstructor
public class ConfirmQuotationController {
	
    //private final Validator validator;
    private final ConfirmQuotationService confirmQuotationService;
    
    // 생성자 주입 방식
//    public ConfirmQuotationController(Validator validator, ConfirmQuotationService confirmQuotationService, ModelMapper modelMapper) {
//        this.validator = validator;
//        this.confirmQuotationService = confirmQuotationService;
//        this.modelMapper = modelMapper;
//    }
    
	/**
	 * 견적 확정 정보 저장
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @PostMapping//행위(method)는 URL에 포함하지 않는다.
    @Tag(name="ConfirmQuotation")
    @Operation(summary = "Insert ConfirmQuotation", description="견적확정 정보 저장")//OpenAPI/Swagger 사양에서 요약, 설명, 매개변수, 응답 코드 등과 같은 특정 API 엔드포인트에 대한 메타데이터를 제공하는 데 사용
	public ResponseEntity<ConfirmQuotationResponse> saveConfirmQuotation(@RequestBody @Valid ConfirmQuotationRequest request) {
    		
    	ConfirmQuotationDTO dto = ConfirmQuotationDTO.builder()
    			.quotationReqNo(request.getQuotationReqNo())
    			.confirmDt(request.getConfirmDt())
    			.custId(request.getCustId())
    			.driverId(request.getDriverId())
    			.build();
    	
    	ConfirmQuotationResponse response = ConfirmQuotationResponse.builder()
    			.results(confirmQuotationService.saveConfirmQuotation(dto))
    			.build();
    	
        return ResponseEntity.ok(response);
	}
    
//    /**
//     * 견적 확정 정보 수정
//     * 수정할 일이 없을 것 같아 @Deprecated 처리
//     * @param reqeust
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    @Deprecated
//    @PutMapping(value = "/{quotationid}") //PUT방식은 전체의 리소스를 교체 할 때 사용하고, PATCH는 리소스의 일부분을 교체 할 때 사용.
//    @Tag(name="ConfirmQuotation")
//    @Operation(summary = "Modify ConfirmQuotation", description="견적확정 정보 수정")
//	public ResponseEntity<ConfirmQuotationResponse> modifyConfirmQuotation(
//			@PathVariable("quotationid") String quotationId,
//			@RequestBody @Valid ConfirmQuotationRequest request) {
//    	
//    	ConfirmQuotationDTO quotation = ConfirmQuotationDTO.builder()
//    			.quotationReqNo(quotationId)
//    			.driverId(request.getDriverId())
//    			.custId(request.getCustId())
//    			.confirmDt(request.getConfirmDt())
//    			.build();
//    	
//    	ConfirmQuotationResponse response = ConfirmQuotationResponse.builder()
//    			.resultRow(confirmQuotationService.modifyConfrimQuotation(quotation))
//    			.build();
//  
//		return ResponseEntity.ok(response);
//	}
    
    /**
     * 견적요청서 삭제
     * @param quotationid
     * @param response
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "/{quotationid}")
    @Tag(name="ConfirmQuotation")
    @Operation(summary = "Delete ConfirmQuotation", description="견적 확정정보 삭제(철회)")
    public ResponseEntity<ConfirmQuotationResponse> deleteReqQuotation(@PathVariable("quotationid") String quotationId) {
    	ConfirmQuotationResponse response = ConfirmQuotationResponse.builder()
    			.results(	confirmQuotationService.deleteConfirmQuotation(quotationId))
    			.build();
    	return ResponseEntity.ok(response);
    }
    
    /**
     * 기사님별 견적확정 정보 조회
     * @param customerId
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/drivers/{driverid}")
    @Tag(name="ConfirmQuotation")
    @Operation(summary = "get ConfirmQuotationList", description="기사님별 견적확정 정보 조회")
    public ResponseEntity<ConfirmQuotationResponse> getConfirmQuotationList(@PathVariable("driverid") String driverId) {

    	List<ConfirmQuotationDTO> ConfirmQuotationList = confirmQuotationService.getConfirmQuotationListByDriver(driverId);
    	ConfirmQuotationResponse response = ConfirmQuotationResponse.builder()
    			.confirmQuotations(ConfirmQuotationList)
    			.build();
    	return ResponseEntity.ok(response);
    }
    
    /**
     * 사용자별 견적확정 정보 조회
     * @param customerId
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/customers/{customerid}")
    @Tag(name="ConfirmQuotation")
    @Operation(summary = "get ConfirmQuotation", description="사용자별 견적 확정정보 조회")
    public ResponseEntity<ConfirmQuotationResponse> getConfirmQuotation(@PathVariable("customerid") String customerId){
    	ConfirmQuotationDTO confirmQuotation = confirmQuotationService.getConfirmQuotationByUser(customerId);
    	ConfirmQuotationResponse response = ConfirmQuotationResponse.builder()
    			.confirmQuotation(confirmQuotation)
    			.build();
    	return ResponseEntity.ok(response);
    }
    
}
