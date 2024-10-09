package com.study.jimcarry.service;

import java.util.List;

import com.study.jimcarry.domain.MovingInfoEntity;
import com.study.jimcarry.model.MovingInfo;

public interface MovingInfoService {
	
	/**
	 * 이사정보 저장(견적 채택)
	 * @param movingInfoEntity
	 * @return
	 */
	int saveMovingInfo(MovingInfoEntity movingInfoEntity);
	
	/**
	 * 이사 내역 전체 조회
	 * @return
	 */
	List<MovingInfo> getMovingInfoList();
	
	/**
	 * 고객별 이사내역 조회
	 * @param customerId
	 * @return
	 */
	MovingInfo getMovingInfoByCustomers(String customerId);
	
	/**
	 * 기사님별 이사내역 조회
	 * @param driverId
	 * @return
	 */
	MovingInfo getMovingInfoByDrivers(String driverId);
	
}
