package com.study.jimcarry.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.study.jimcarry.domain.RoleEntity;
import com.study.jimcarry.domain.UserEntity;
import com.study.jimcarry.exception.CustomException;
import com.study.jimcarry.exception.ErrorCode;
import com.study.jimcarry.mapper.RoleMapper;
import com.study.jimcarry.mapper.UserMapper;
import com.study.jimcarry.model.UserInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor //Lombok에서 제공하는 어노테이션으로, final이나 @NonNull이 붙은 필드에 대해 생성자를 자동으로 생성해주는 역할
public class AuthService {
	
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

	public int saveUserInfo(UserInfo userInfo) {

		UserEntity findEntity = userMapper.findUserById(userInfo.getUserId());
		if(findEntity != null) {
			throw new CustomException(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
		}
		
		//Builder 패턴으로 컨트롤러에서 넘겨 받은 UserInfo를 UserEntity로 변환
		UserEntity userEntity = UserEntity.builder()
				.userId(userInfo.getUserId())
				.userName(userInfo.getUserName())
				.password(DigestUtils.sha256Hex(userInfo.getPassword()))
				.phoneNumber(userInfo.getPhoneNumber())
				.email(userInfo.getEmail())
				.userType(userInfo.getUserType())
				.build();
		
		return userMapper.insertUserInfo(userEntity);
	}

	public int saveRoleInfo(RoleEntity roleEntity) {
		return roleMapper.insertRoleInfo(roleEntity);
	}

	public String findUserId(UserInfo userInfo) {
		
		//Builder 패턴으로 컨트롤러에서 넘겨 받은 UserInfo를 UserEntity로 변환
				UserEntity userEntity = UserEntity.builder()
						.userName(userInfo.getUserName())
						.email(userInfo.getEmail())
						.build();
				
		UserEntity findUser = userMapper.findUserId(userEntity);
		if(findUser.getUserId() == null) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), "사용자 정보가 다릅니다.");
		}
		
		//컨트롤러로 넘겨 줄 때 다시 userInfo의 빌더 패턴으로 넘겨 줘야 할까 ??
		return findUser.getUserId();
	}

	public int changePassword(UserInfo userInfo) {
		
		UserEntity findUser = userMapper.findUserById(userInfo.getUserId());
		if(!findUser.getPassword().equals(DigestUtils.sha256Hex(userInfo.getCurrentPassword()))) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), "현재 암호가 다릅니다!");
		} else if(findUser.getPassword().equals(userInfo.getPassword())) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), "이전 암호와 동일 합니다.");
		}
		
		//변경 할 암호를 SHA256으로 암호화
		UserEntity userEntity = UserEntity.builder()
				.password(DigestUtils.sha256Hex(userInfo.getPassword()))
				.build();
		
		return userMapper.changePassword(userEntity);
	}
	
}
