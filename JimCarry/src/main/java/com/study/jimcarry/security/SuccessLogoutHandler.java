package com.study.jimcarry.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.jimcarry.api.LogoutResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SuccessLogoutHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
		try {
			
			LogoutResponse logoutRes = new LogoutResponse();
			
	        // LogoutResponse 객체를 JSON으로 변환
	        ObjectMapper objectMapper = new ObjectMapper();
	        String jsonResponse = objectMapper.writeValueAsString(logoutRes);

			response.setStatus(HttpStatus.OK.value());
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonResponse);
			response.getWriter().flush();
			response.getWriter().close();

		} catch ( Exception e ) {
            log.error("{}", e);
		}
	}

}
