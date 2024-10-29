package com.study.jimcarry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.study.jimcarry.api.QuotationAcceptRequest;
import com.study.jimcarry.model.QuotationAcceptDTO;
import com.study.jimcarry.service.QuotationAcceptService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes= {JimCarryApplication.class})
@Transactional
@Rollback(false)
@Slf4j
public class QuotationAcceptTest {

	@Autowired
	QuotationAcceptService quotationAcceptService;
	
	@Test
	@DisplayName("견적채택 테스트")
	void insertQuotationAcceptTest() {
		//given
		QuotationAcceptRequest req = new QuotationAcceptRequest();
		req.setQuotationReqNo("ReqQuo_3714c6fb-faf5-4794-9b65-ca07a5b2a0cc");
		req.setCustId("customer_123");
		req.setDriverId("driver_123");
		req.setMovingState("0");
		req.setCid(0);
		
		QuotationAcceptDTO dto = QuotationAcceptDTO.builder()
				.quotationReqNo(req.getQuotationReqNo())
				.custId(req.getCustId())
				.driverId(req.getDriverId())
				.movingState(req.getMovingState())
				.cid(req.getCid())
				.build();
		
		//when
		quotationAcceptService.saveQuotationAccept(dto);
        
        
		//then
		
	}
	
}
