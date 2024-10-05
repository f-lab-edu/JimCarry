package com.study.jimcarry.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.study.jimcarry.domain.MovingInfoEntity;

@Mapper
public interface MovingInfoMapper {
	
	/**
	 * 이사 채택
	 * @param movingInfoEntity
	 * @return
	 */
	int insertMovingInfo(MovingInfoEntity movingInfoEntity);
}
