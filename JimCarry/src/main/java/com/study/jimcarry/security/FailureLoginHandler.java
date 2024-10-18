package com.study.jimcarry.security;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.jimcarry.api.LoginResponse;
import com.study.jimcarry.exception.ErrorCode;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FailureLoginHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException e) throws IOException, ServletException {

    	LoginResponse loginResponse = new LoginResponse();
		String remoteIp = request.getHeader("X-FORWARDED-FOR");
		if ( StringUtils.isBlank(remoteIp) ) { remoteIp = request.getRemoteAddr(); }
        
		//계정 암호가 틀렸을 때
		if(e instanceof BadCredentialsException) {
			log.debug("암호 틀림!");
			loginResponse.setError(ErrorCode.NOT_FOUND);
		} 
		//계정을 찾을 수 없을 때
		else if(e instanceof UsernameNotFoundException) {
			log.debug("계정 없음!");
			loginResponse.setError(ErrorCode.NOT_FOUND);
		} 
		
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(loginResponse);
        
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
        response.getWriter().close();
    }
}