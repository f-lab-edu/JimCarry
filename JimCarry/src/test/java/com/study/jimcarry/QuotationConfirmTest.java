package com.study.jimcarry;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.study.jimcarry.api.ConfirmQuotationRequest;
import com.study.jimcarry.model.ConfirmQuotationDTO;
import com.study.jimcarry.service.ConfirmQuotationService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes= {JimCarryApplication.class})
@Transactional
@Rollback(false)
@Slf4j
public class QuotationConfirmTest {

	@Autowired
	ConfirmQuotationService confirmQuotationService;
	
	@Test
	@DisplayName("견적확정 테스트")
	void insertQuotationReqTest() {
		//given
		ConfirmQuotationRequest req = new ConfirmQuotationRequest();
		req.setQuotationReqNo("ReqQuo_3714c6fb-faf5-4794-9b65-ca07a5b2a0cc");
		req.setConfirmDt(LocalDateTime.now());
		req.setCustId("customer_123");
		req.setDriverId("driver_123");
		req.setCid(0);
   
		ConfirmQuotationDTO dto = ConfirmQuotationDTO.builder()
		.quotationReqNo(req.getQuotationReqNo())
		.confirmDt(req.getConfirmDt())
		.custId(req.getCustId())
		.driverId(req.getDriverId())
		.build();
		
		//when
		confirmQuotationService.saveConfirmQuotation(dto);
        
        
		//then
		
	}
	
}
