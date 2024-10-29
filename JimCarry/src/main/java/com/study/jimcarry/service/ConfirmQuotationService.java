package com.study.jimcarry.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.study.jimcarry.domain.ConfirmQuotationEntity;
import com.study.jimcarry.exception.CustomException;
import com.study.jimcarry.exception.ErrorCode;
import com.study.jimcarry.mapper.ConfirmQuotationMapper;
import com.study.jimcarry.model.ConfirmQuotationDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor //Lombok에서 제공하는 어노테이션으로, final이나 @NonNull이 붙은 필드에 대해 생성자를 자동으로 생성해주는 역할
public class ConfirmQuotationService {
	
    private final ConfirmQuotationMapper confirmQuotationMapper;
    
	/**
	 * 견적확정 정보 저장 
	 * @param confirmQuotationEntity
	 * @return
	 */
	public int saveConfirmQuotation(ConfirmQuotationDTO confirmQuotation) {
		ConfirmQuotationEntity confirmQuotationEntity = ConfirmQuotationEntity.builder()
				.quotationReqNo(confirmQuotation.getQuotationReqNo())
				.confirmDt(confirmQuotation.getConfirmDt())
				.custId(confirmQuotation.getCustId())
				.driverId(confirmQuotation.getDriverId())
				.cid(0)
				.build();
		return confirmQuotationMapper.insertConfirmQuotation(confirmQuotationEntity);
	}

	/**
	 * 견적확정 정보 수정
	 * @param confirmQuotationEntity
	 * @return
	 */
	public int modifyConfrimQuotation(ConfirmQuotationDTO confirmQuotation) {
		
		ConfirmQuotationEntity confirmQuotationEntity = ConfirmQuotationEntity.builder()
				.quotationReqNo(confirmQuotation.getQuotationReqNo())
				.confirmDt(confirmQuotation.getConfirmDt())
				.custId(confirmQuotation.getCustId())
				.driverId(confirmQuotation.getDriverId())
				.build();
		
		ConfirmQuotationEntity findConfirmQuotation = confirmQuotationMapper.selectConfrimQuotation(confirmQuotationEntity.getQuotationReqNo());
		if(findConfirmQuotation == null) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
		}
		return confirmQuotationMapper.updateConfirmQuotation(confirmQuotationEntity);
	}
	
	/**
	 * 견적 확정정보 삭제(철회)
	 * @param reqQuotationId
	 * @return
	 */
	public int deleteConfirmQuotation(String reqQuotationId) {
		return confirmQuotationMapper.deleteConfirmQuotation(reqQuotationId);
	}
	
	/**
	 * 기사님별 견적확정 정보 조회
	 * @return
	 */
	public List<ConfirmQuotationDTO> getConfirmQuotationListByDriver(String driverId) {
		List<ConfirmQuotationEntity> findList = confirmQuotationMapper.selectConfirmQuotationByDriver(driverId);
		List<ConfirmQuotationDTO> confirmQuotationList = new ArrayList<>();
		for(ConfirmQuotationEntity entity : findList) {
			ConfirmQuotationDTO confirmQuotation = ConfirmQuotationDTO.builder()
					.quotationReqNo(entity.getQuotationReqNo())
					.confirmDt(entity.getConfirmDt())
					.custId(entity.getCustId())
					.driverId(entity.getDriverId())
					.build();
			confirmQuotationList.add(confirmQuotation);
		}
		return confirmQuotationList;
	}
	
	/**
	 * 사용자별 견적확정 정보 조회
	 * @param customerId
	 * @return
	 */
	public ConfirmQuotationDTO getConfirmQuotationByUser(String customerId) {
		ConfirmQuotationEntity entity = confirmQuotationMapper.selectConfirmQuotationByUser(customerId);
		if(entity == null) {
			throw new CustomException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
		}
		ConfirmQuotationDTO confirmQuotation = ConfirmQuotationDTO.builder()
				.quotationReqNo(entity.getQuotationReqNo())
				.confirmDt(entity.getConfirmDt())
				.custId(entity.getCustId())
				.driverId(entity.getDriverId())
				.build();;
		return confirmQuotation;	
	}
	
}
