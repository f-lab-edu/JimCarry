package com.study.jimcarry.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.jimcarry.domain.MovingInfoEntity;
import com.study.jimcarry.mapper.MovingInfoMapper;
import com.study.jimcarry.mapper.ReqQuotationMapper;
import com.study.jimcarry.model.MovingInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovingInfoService {
    private final MovingInfoMapper movingMapper;
    private final ReqQuotationMapper reqQuotationMapper;
    
	/**
	 * 이사정보 저장(견적 채택)
	 * @param MovingInfo
	 * @return
	 */
	@Transactional
	public int saveMovingInfo(MovingInfo movingInfo) {
		MovingInfoEntity movingInfoEntity = MovingInfoEntity.builder()
				.reqQuotationId(movingInfo.getReqQuotationId())
				.acceptQuotationDt(movingInfo.getAcceptQuotationDt())
				.customerId(movingInfo.getCustomerId())
				.driverId(movingInfo.getDriverId())
				.movingState(movingInfo.getMovingState())
				.build();			
		//이사정보에 insert전 견적요청테이블에 채택 여부 Y로 update
		reqQuotationMapper.updateReqQuotationIsAccepted(movingInfoEntity.getReqQuotationId(), true);
		return movingMapper.insertMovingInfo(movingInfoEntity);
	}
	
	/**
	 * 이사 내역 전체 조회
	 * @return
	 */
	public List<MovingInfo> getMovingInfoList() {
		List<MovingInfoEntity> list = movingMapper.selectMovingInfoList();
		List<MovingInfo> movingInfoList = new ArrayList<>();
		for(MovingInfoEntity entity : list) {
			MovingInfo movingInfo = MovingInfo.builder()
					.reqQuotationId(entity.getReqQuotationId())
					.acceptQuotationDt(entity.getAcceptQuotationDt())
					.customerId(entity.getCustomerId())
					.driverId(entity.getDriverId())
					.movingState(entity.getMovingState())
					.build();
			movingInfoList.add(movingInfo);
		}
		return movingInfoList;
	}
	
	/**
	 * 고객별 이사내역 조회
	 * @param customerId
	 * @return
	 */
	public MovingInfo getMovingInfoByCustomers(String customerId) {
		MovingInfoEntity entity = movingMapper.selectMovingInfoByCustomers(customerId);
		MovingInfo movingInfo = MovingInfo.builder()
				.reqQuotationId(entity.getReqQuotationId())
				.acceptQuotationDt(entity.getAcceptQuotationDt())
				.customerId(entity.getCustomerId())
				.driverId(entity.getDriverId())
				.movingState(entity.getMovingState())
				.build();
		return movingInfo;
	}
	
	/**
	 * 기사님별 이사내역 조회
	 * @param driverId
	 * @return
	 */
	public MovingInfo getMovingInfoByDrivers(String driverId) {
		MovingInfoEntity entity = movingMapper.selectMovingInfoByDrivers(driverId);
		MovingInfo movingInfo = MovingInfo.builder()
				.reqQuotationId(entity.getReqQuotationId())
				.acceptQuotationDt(entity.getAcceptQuotationDt())
				.customerId(entity.getCustomerId())
				.driverId(entity.getDriverId())
				.movingState(entity.getMovingState())
				.build();
		return movingInfo;
	}
	
}
