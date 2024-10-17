package com.study.jimcarry.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.study.jimcarry.domain.UserEntity;
import com.study.jimcarry.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserMapper userMapper;
	
	public CustomUserDetailsService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		UserEntity user = userMapper.selectUserById(userName);
		
		if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
		
		CustomUserDetail loginUser = new CustomUserDetail();
		loginUser.setUserId(user.getUserId());
		loginUser.setUserName(user.getUserName());
		loginUser.setPassword(user.getPassword());
		loginUser.setPhoneNumber(user.getPassword());
		loginUser.setEmail(user.getEmail());
		loginUser.setUserType(user.getUserType());
		
		return loginUser;
	}

}
