package com.study.jimcarry.mockup;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.jimcarry.JimCarryApplication;
import com.study.jimcarry.api.ReqQuotationRequest;

import lombok.extern.slf4j.Slf4j;

@AutoConfigureMockMvc
@SpringBootTest(classes= {JimCarryApplication.class})
@Transactional
@Rollback(false)
@Slf4j
public class ReqQuotationMockUpTest {

	@Autowired
    private MockMvc mockMvc;
	
	@Test
	public void saveReqQuotationTest() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		ReqQuotationRequest request = new ReqQuotationRequest();
	
		String jsonString = objectMapper.writeValueAsString(request);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/req-quotation/save")
				.contentType("application/json;charset=UTF-8")
				.content(jsonString);
		
		MvcResult mvcResult = mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print())
				.andReturn();
		
		Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		
		String body = mvcResult.getResponse().getContentAsString();
		log.debug("response: {}", body);
	
	}
	
	@Test
	public void getReqQuotationListTest() throws Exception {
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/req-quotation/list");
		
		MvcResult mvcResult = mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print())
				.andReturn();
		
		Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		
		String body = mvcResult.getResponse().getContentAsString();
		log.debug("response: {}", body);
		
	}
}
