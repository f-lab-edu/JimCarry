package com.study.jimcarry.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.jimcarry.domain.QuotationAcceptEntity;
import com.study.jimcarry.mapper.QuotationAcceptMapper;
import com.study.jimcarry.mapper.ReqQuotationMapper;
import com.study.jimcarry.model.QuotationAcceptDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuotationAcceptService {
	
    private final QuotationAcceptMapper quotationAcceptMapper;
    
	/**
	 * 견적 채택
	 * @param QuotationAcceptDTO
	 * @return
	 */
	@Transactional
	public int saveQuotationAccept(QuotationAcceptDTO dto) {
		QuotationAcceptEntity entity = QuotationAcceptEntity.builder()
				.quotationReqNo(dto.getQuotationReqNo())
				.acceptDt(dto.getAcceptDt())
				.custId(dto.getCustId())
				.driverId(dto.getDriverId())
				.movingState(dto.getMovingState())
				.build();			
		return quotationAcceptMapper.insertQuotationAccept(entity);
	}
	
	/**
	 * 이사 내역 전체 조회
	 * @return
	 */
	public List<QuotationAcceptDTO> getQuotationAcceptList() {
		List<QuotationAcceptEntity> list = quotationAcceptMapper.selectQuotationAcceptList();
		List<QuotationAcceptDTO> quotationAcceptList = new ArrayList<>();
		for(QuotationAcceptEntity entity : list) {
			QuotationAcceptDTO dto = QuotationAcceptDTO.builder()
					.quotationReqNo(entity.getQuotationReqNo())
					.acceptDt(entity.getAcceptDt())
					.custId(entity.getCustId())
					.driverId(entity.getDriverId())
					.movingState(entity.getMovingState())
					.build();
			quotationAcceptList.add(dto);
		}
		return quotationAcceptList;
	}
	
	/**
	 * 고객별 이사내역 조회
	 * @param customerId
	 * @return
	 */
	public QuotationAcceptDTO getQuotationAcceptByCustomers(String customerId) {
		QuotationAcceptEntity entity = quotationAcceptMapper.selectQuotationAcceptByCustomers(customerId);
		QuotationAcceptDTO dto = QuotationAcceptDTO.builder()
				.quotationReqNo(entity.getQuotationReqNo())
				.acceptDt(entity.getAcceptDt())
				.custId(entity.getCustId())
				.driverId(entity.getDriverId())
				.movingState(entity.getMovingState())
				.build();
		return dto;
	}
	
	/**
	 * 기사님별 이사내역 조회
	 * @param driverId
	 * @return
	 */
	public QuotationAcceptDTO getQuotationAcceptByDrivers(String driverId) {
		QuotationAcceptEntity entity = quotationAcceptMapper.selectQuotationAcceptByDrivers(driverId);
		QuotationAcceptDTO dto = QuotationAcceptDTO.builder()
				.quotationReqNo(entity.getQuotationReqNo())
				.acceptDt(entity.getAcceptDt())
				.custId(entity.getCustId())
				.driverId(entity.getDriverId())
				.movingState(entity.getMovingState())
				.build();
		return dto;
	}
	
}
