package com.study.jimcarry.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.study.jimcarry.config.ApplicationContextProvider;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		UserDetailsService userDetails = (UserDetailsService) ApplicationContextProvider.getApplicationContext().getBean(UserDetailsService.class);
		CustomUserDetail user = (CustomUserDetail) userDetails.loadUserByUsername(username);

		// SHA256으로 비밀번호 설정
		String shaPassword = DigestUtils.sha256Hex(password);

		// 패스워드 틀렸을 경우 
		if (!StringUtils.equals(shaPassword, user.getPassword())) {
			throw new BadCredentialsException("사용자 계정이 다릅니다.");
		}
        
		// 계정이 비활성화 된 경우
//        if (!user.isEnabled()) {
//            throw new DisabledException("login.account.deactivated");
//        }
        
		// user 정보는 details가 아니라 principal에 설정함
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
        
        return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
