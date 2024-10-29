package com.study.jimcarry;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.study.jimcarry.api.MoveItemRequest;
import com.study.jimcarry.api.ReqQuotationRequest;
import com.study.jimcarry.model.MoveItemDTO;
import com.study.jimcarry.model.ReqQuotationDTO;
import com.study.jimcarry.service.ReqQuotationService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes= {JimCarryApplication.class})
@Transactional
@Rollback(false)
@Slf4j
public class ReqQuotationTest {

	@Autowired
	ReqQuotationService reqQuotationService;
	
	@Test
	@DisplayName("견적채택 테스트")
	void insertQuotationReqTest() {
		//given
		ReqQuotationRequest request = new ReqQuotationRequest();
        request.setQuotationDt(LocalDateTime.now()); // set a valid date
        request.setCustId("customer_123");
        request.setPickupAddr("Seoul, Korea");
        request.setDeliveryAddr("Busan, Korea");
        request.setMoveDt(LocalDate.now()); // set a valid move date
        request.setQuotationAmount(BigDecimal.valueOf(1000000.00));
        request.setBuildingType("Apartment");
        request.setRoomStructure("2 rooms");
        request.setHouseSize(BigDecimal.valueOf(24));
        request.setHasElevator(true);
        request.setBoxCount(30);
        request.setCid(1);
        
        //이사 짐 정보
        List<MoveItemRequest> moveItemList = new ArrayList<>();
        MoveItemRequest moveReq1= new MoveItemRequest();
        moveReq1.setFurnitureId(1);
        moveReq1.setOptionValId(1);
        moveReq1.setQty(1);
        moveItemList.add(moveReq1);
        
        MoveItemRequest moveReq2= new MoveItemRequest();
        moveReq2.setFurnitureId(2);
        moveReq2.setOptionValId(2);
        moveReq2.setQty(1);
        moveItemList.add(moveReq2);
        
        MoveItemRequest moveReq3= new MoveItemRequest();
        moveReq3.setFurnitureId(2);
        moveReq3.setOptionValId(2);
        moveReq3.setQty(1);
        moveItemList.add(moveReq3);
        
        request.setMoveItemList(moveItemList);
        
        
    	ReqQuotationDTO dto = ReqQuotationDTO.builder()
	            .quotationDt(request.getQuotationDt())
	            .custId(request.getCustId())
	            .pickupAddr(request.getPickupAddr())
	            .deliveryAddr(request.getDeliveryAddr())
	            .moveDt(request.getMoveDt())
	            .buildingType(request.getBuildingType())
	            .roomStructure(request.getRoomStructure())
	            .houseSize(request.getHouseSize())
	            .hasElevator(request.isHasElevator())
	            .boxCount(request.getBoxCount())
	            .quotationAmount(request.getQuotationAmount())
	            .build();
		
    	List<MoveItemDTO> dtoList = new ArrayList<>();
    	for(MoveItemRequest mvReq : request.getMoveItemList()) {
    		MoveItemDTO mvDto = MoveItemDTO.builder()
    				.furnitureId(mvReq.getFurnitureId())
    				.optionValId(mvReq.getOptionValId())
    				.qty(mvReq.getQty())
    				.build();
    		dtoList.add(mvDto);
    	}
    	
		//when
        reqQuotationService.saveReqQuotation(dto, dtoList);
        
        
		//then
		
	}
	
}
