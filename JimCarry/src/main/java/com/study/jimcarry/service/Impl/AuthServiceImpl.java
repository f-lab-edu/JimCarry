package com.study.jimcarry.service.Impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.study.jimcarry.domain.RoleEntity;
import com.study.jimcarry.domain.UserEntity;
import com.study.jimcarry.exception.CustomException;
import com.study.jimcarry.exception.ErrorCode;
import com.study.jimcarry.mapper.RoleMapper;
import com.study.jimcarry.mapper.UserMapper;
import com.study.jimcarry.service.AuthService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
	
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final ModelMapper modelMapper;

    // 생성자 주입
    public AuthServiceImpl(
            UserMapper userMapper,
            RoleMapper roleMapper,
            ModelMapper modelMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.modelMapper = modelMapper;
    }
    
	@Override
	public int saveUserInfo(UserEntity userEntity) {
		log.debug(" #### saveUserInfo entity #### {} ", userEntity);
		
		UserEntity findEntity = userMapper.selectUserById(userEntity.getUserName());
		if(findEntity != null) {
			throw new CustomException(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
		}
		
		return userMapper.insertUserInfo(userEntity);
	}

	@Override
	public int saveRoleInfo(RoleEntity roleEntity) {
		return roleMapper.insertRoleInfo(roleEntity);
	}

	@Override
	public String findUserId(UserEntity userEntity) {
		UserEntity findUser = userMapper.findUserId(userEntity);
		if(findUser.getUserId() == null) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), "사용자 정보가 다릅니다.");
		}
		
		return findUser.getUserId();
	}

	@Override
	public int changePassword(UserEntity userEntity, String curPassword) {
		UserEntity findUser = userMapper.selectUserById(userEntity.getUserId());
		if(!findUser.getPassword().equals(DigestUtils.sha256Hex(curPassword))) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), "현재 암호가 다릅니다!");
		} else if(findUser.getPassword().equals(userEntity.getPassword())) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), "이전 암호와 동일 합니다.");
		}
		
		return userMapper.changePassword(userEntity);
	}

}
