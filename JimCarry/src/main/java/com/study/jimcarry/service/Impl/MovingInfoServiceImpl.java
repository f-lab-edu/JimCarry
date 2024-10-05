package com.study.jimcarry.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.study.jimcarry.domain.MovingInfoEntity;
import com.study.jimcarry.mapper.MovingInfoMapper;
import com.study.jimcarry.mapper.ReqQuotationMapper;
import com.study.jimcarry.service.MovingInfoService;

public class MovingInfoServiceImpl implements MovingInfoService {
	
	@Autowired
	private MovingInfoMapper movingMapper;
	
	@Autowired
	private ReqQuotationMapper ReqQuotationMapper;
	
	@Override
	@Transactional
	public int saveMovingInfo(MovingInfoEntity movingInfoEntity) {
		//이사정보에 insert전 견적요청테이블에 채택 여부 Y로 update
		ReqQuotationMapper.updateReqQuotationIsAccepted(movingInfoEntity.getReqQuotationId(), true);
		return movingMapper.insertMovingInfo(movingInfoEntity);
	}

}
