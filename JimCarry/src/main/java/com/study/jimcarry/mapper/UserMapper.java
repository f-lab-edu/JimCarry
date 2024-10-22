package com.study.jimcarry.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.study.jimcarry.domain.UserEntity;

@Mapper
public interface UserMapper {
	
	/**
	 * 사용자 생성
	 * @param userEntity
	 * @return
	 */
	int insertUserInfo(UserEntity userEntity);
	
	/**
	 * 사용자 정보 조회
	 * @param userId
	 * @return
	 */
	UserEntity findUserById(String userId);
	
	
	/**
	 * 사용자 아이디 찾기
	 * @param userEntity
	 * @return
	 */
	UserEntity findUserId(UserEntity userEntity);
	
	/**
	 * 비밀번호 변경
	 * @param userEntity
	 * @return
	 */
	int changePassword(UserEntity userEntity);
}
