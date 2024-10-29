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

import com.study.jimcarry.api.QuotationAcceptRequest;
import com.study.jimcarry.api.QuotationAcceptResponse;
import com.study.jimcarry.api.ReqQuotaionResponse;
import com.study.jimcarry.domain.QuotationAcceptEntity;
import com.study.jimcarry.domain.ReqQuotationEntity;
import com.study.jimcarry.model.QuotationAcceptDTO;
import com.study.jimcarry.service.ConfirmQuotationService;
import com.study.jimcarry.service.QuotationAcceptService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name="QuotationAccept", description="QuotationAccept API") //OpenAPI/Swagger에서 API를 그룹화하는데 사용
@RestController
@RequestMapping(value = "/api/quotation-accepts")//해당 Controller의 URL을 지정
@RequiredArgsConstructor
public class QuotationAcceptController {
	
//    private final Validator validator;
    private final QuotationAcceptService quotationAcceptService;
//    private final ModelMapper modelMapper;

    // 생성자 주입 방식 생성자가 1개 일때 @Autowired 생략이 가능
//    public MovinfInfoController(Validator validator, MovingInfoService movingInfoService, ModelMapper modelMapper) {
//        this.validator = validator;
//        this.movingInfoService = movingInfoService;
//        this.modelMapper = modelMapper;
//    }
	
    @PostMapping //행위(method)는 URL에 포함하지 않는다.
    @Tag(name="QuotationAccept")
    @Operation(summary = "Insert QuotationAccept", description="견적 채택")//OpenAPI/Swagger 사양에서 요약, 설명, 매개변수, 응답 코드 등과 같은 특정 API 엔드포인트에 대한 메타데이터를 제공하는 데 사용
	public ResponseEntity<QuotationAcceptResponse> saveQuotationAccept(@RequestBody @Valid QuotationAcceptRequest request) {
    	
		QuotationAcceptDTO dto = QuotationAcceptDTO.builder()
				.quotationReqNo(request.getQuotationReqNo())
				.acceptDt(request.getAcceptDt())
				.custId(request.getCustId())
				.driverId(request.getDriverId())
				.movingState(request.getMovingState())
				.build();
		
		QuotationAcceptResponse response = QuotationAcceptResponse.builder()
				.resultRow(quotationAcceptService.saveQuotationAccept(dto))
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
    @Tag(name="QuotationAccept")
    @Operation(summary = "get QuotationAcceptnList", description="이사정보 전체 조회")
    public ResponseEntity<QuotationAcceptResponse> getReqQuotaionList() {
    	List<QuotationAcceptDTO> quotationAcceptList = quotationAcceptService.getQuotationAcceptList();
		QuotationAcceptResponse response = QuotationAcceptResponse.builder()
				.quotationAcceptList(quotationAcceptList)
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
    @Tag(name="QuotationAccept")
    @Operation(summary = "get QuotationAcceptByCustomer", description="사용자별 이사정보 조회")
    public ResponseEntity<QuotationAcceptResponse> getMovingInfoByCustomer(@PathVariable("customerid") String customerId) {
    	QuotationAcceptDTO dto = quotationAcceptService.getQuotationAcceptByCustomers(customerId);
		QuotationAcceptResponse response = QuotationAcceptResponse.builder()
				.quotationAccept(dto)
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
    @Tag(name="QuotationAccept")
    @Operation(summary = "get QuotationAcceptByDriver", description="기사님별 견적채택 조회")
    public ResponseEntity<QuotationAcceptResponse> getQuotationAcceptBydriver(@PathVariable("driverid") String driverId) {
    	QuotationAcceptDTO dto = quotationAcceptService.getQuotationAcceptByDrivers(driverId);
		QuotationAcceptResponse response = QuotationAcceptResponse.builder()
				.quotationAccept(dto)
				.build();
        return ResponseEntity.ok(response);
    }
}
