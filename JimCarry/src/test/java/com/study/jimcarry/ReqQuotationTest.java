package com.study.jimcarry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.study.jimcarry.model.ReqQuotation;
import com.study.jimcarry.service.ReqQuotationService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes= {JimCarryApplication.class})
@Transactional
@Rollback(false)
@Slf4j
public class ReqQuotationTest {

	@Autowired
	ReqQuotationService reqQuotationService;
	
	@Test
	@DisplayName("견적요청 조회 테스트")
	void selectReqQuotatoinTest() {
		
		String customerId = "cust0001";
		
		ReqQuotation reqQuotation = reqQuotationService.getReqQuotationByUser(customerId);
		
		log.debug("{}", reqQuotation);
	}
	
	
}
