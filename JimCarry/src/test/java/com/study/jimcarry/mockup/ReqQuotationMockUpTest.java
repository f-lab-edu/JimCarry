package com.study.jimcarry.mockup;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.jimcarry.api.ReqQuotationRequest;
import com.study.jimcarry.controller.ReqQuotationController;
import com.study.jimcarry.service.ReqQuotationService;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(ReqQuotationController.class) 
@Slf4j
public class ReqQuotationMockUpTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // ReqQuotationService를 Mock으로 설정
    private ReqQuotationService reqQuotationService;
    
    @MockBean
    private ModelMapper modelMapper; // ModelMapper를 Mock으로 설정
    
    @Test
    public void saveReqQuotationTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        ReqQuotationRequest request = new ReqQuotationRequest();

        String jsonString = objectMapper.writeValueAsString(request);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/req-quotation")
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
