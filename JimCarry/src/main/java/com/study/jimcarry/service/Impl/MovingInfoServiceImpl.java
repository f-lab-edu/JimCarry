package com.study.jimcarry.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.study.jimcarry.domain.MovingInfoEntity;
import com.study.jimcarry.mapper.MovingInfoMapper;
import com.study.jimcarry.mapper.ReqQuotationMapper;
import com.study.jimcarry.model.MovingInfo;
import com.study.jimcarry.service.MovingInfoService;

public class MovingInfoServiceImpl implements MovingInfoService {
	
    private final MovingInfoMapper movingMapper;
    private final ReqQuotationMapper reqQuotationMapper;
    private final ModelMapper modelMapper;
    
    //생성자 주입 방식
    public MovingInfoServiceImpl(MovingInfoMapper movingMapper, 
                                  ReqQuotationMapper reqQuotationMapper, 
                                  ModelMapper modelMapper) {
        this.movingMapper = movingMapper;
        this.reqQuotationMapper = reqQuotationMapper;
        this.modelMapper = modelMapper;
    }
	
	@Override
	@Transactional
	public int saveMovingInfo(MovingInfoEntity movingInfoEntity) {
		//이사정보에 insert전 견적요청테이블에 채택 여부 Y로 update
		reqQuotationMapper.updateReqQuotationIsAccepted(movingInfoEntity.getReqQuotationId(), true);
		return movingMapper.insertMovingInfo(movingInfoEntity);
	}

	@Override
	public List<MovingInfo> getMovingInfoList() {
		List<MovingInfoEntity> list = movingMapper.selectMovingInfoList();
		List<MovingInfo> movingInfoList = new ArrayList<>();
		for(MovingInfoEntity entity : list) {
			MovingInfo movingInfo = modelMapper.map(entity, MovingInfo.class);
			movingInfoList.add(movingInfo);
		}
		return movingInfoList;
	}

	@Override
	public MovingInfo getMovingInfoByCustomers(String customerId) {
		MovingInfoEntity entity = movingMapper.selectMovingInfoByCustomers(customerId);
		MovingInfo movingInfo = modelMapper.map(entity, MovingInfo.class);
		return movingInfo;
	}

	@Override
	public MovingInfo getMovingInfoByDrivers(String driverId) {
		MovingInfoEntity entity = movingMapper.selectMovingInfoByDrivers(driverId);
		MovingInfo movingInfo = modelMapper.map(entity, MovingInfo.class);
		return movingInfo;
	}

}
