package com.study.jimcarry.security;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.jimcarry.api.LoginResponse;
import com.study.jimcarry.model.UserInfoDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SuccessLoginHandler implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {

		try {

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			CustomUserDetail loginUser = (CustomUserDetail)authentication.getPrincipal();
	        
	        //사용자 정보
			UserInfoDTO userInfo = UserInfoDTO.builder()
					.userId(loginUser.getUserId())
					.userName(loginUser.getUserName())
					.userType(loginUser.getUserType())
					.password(loginUser.getPassword())
					.email(loginUser.getEmail())
					.build();
		
	        // LoginResponse 객체를 JSON으로 변환
	        ObjectMapper objectMapper = new ObjectMapper();
	        String jsonResponse = objectMapper.writeValueAsString(new LoginResponse(userInfo));
			log.debug("jsonResponse {}", jsonResponse);
			
	        // Response 설정 및 JSON 전송
	        response.setStatus(HttpStatus.OK.value());
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(jsonResponse);
	        response.getWriter().flush();
	        response.getWriter().close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
