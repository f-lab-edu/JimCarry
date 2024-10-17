package com.study.jimcarry.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.study.jimcarry.domain.RoleEntity;

@Mapper
public interface RoleMapper {
	
	/**
	 * 권한 생성
	 * @param roleEntity
	 * @return
	 */
	int insertRoleInfo(RoleEntity roleEntity);
}
