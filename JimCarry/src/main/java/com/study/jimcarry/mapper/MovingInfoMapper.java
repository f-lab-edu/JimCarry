package com.study.jimcarry.mapper;

import java.util.List;

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
	
	/**
	 * 이사내역 전체 조회
	 * @return
	 */
	List<MovingInfoEntity> selectMovingInfoList();
	
	/**
	 * 고객별 이사내역 조회
	 * @param customerId
	 * @return
	 */
	MovingInfoEntity selectMovingInfoByCustomers(String customerId);
	
	/**
	 * 기사님별 이사내역 조회
	 * @param driverId
	 * @return
	 */
	MovingInfoEntity selectMovingInfoByDrivers(String driverId);
}
