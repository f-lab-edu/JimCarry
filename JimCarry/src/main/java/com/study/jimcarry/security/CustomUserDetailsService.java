package com.study.jimcarry.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.study.jimcarry.domain.UserEntity;
import com.study.jimcarry.mapper.ReqQuotationMapper;
import com.study.jimcarry.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserMapper userMapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		UserEntity user = userMapper.findUserById(userId);
		
		if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        return CustomUserDetail.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .userType(user.getUserType())
                .build();
	}

}
