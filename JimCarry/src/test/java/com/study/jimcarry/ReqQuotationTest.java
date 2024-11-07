package com.study.jimcarry;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
import com.study.jimcarry.model.UpdateReqQuotationDTO;
import com.study.jimcarry.service.ReqQuotationService;

import lombok.extern.slf4j.Slf4j;

//대상이되는 Service만 테스트, 단위테스트로 변경(특정레이어), Mock객체 생성
@SpringBootTest(classes= {JimCarryApplication.class})
@Transactional
@Rollback(false)
@Slf4j
public class ReqQuotationTest {

	@Autowired
	ReqQuotationService reqQuotationService;
	
	@Test
	@DisplayName("견적 작성 테스트")
	void insertQuotationReqTest() throws Exception{
		
		
		//given
		String strDate = "    2024-11-02 00:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(strDate);
		
		ReqQuotationRequest request = new ReqQuotationRequest();
        request.setCustId("3");
        request.setPickupAddr("Seoul, Korea");
        request.setDeliveryAddr("Busan, Korea");
        request.setMoveDt(date); // set a valid move date
        request.setQuotationAmount(BigDecimal.valueOf(1000000.00));
        request.setBuildingType("Apartment");
        request.setRoomStructure("2 rooms");
        request.setHouseSize(24);
        request.setHasElevator(true);
        request.setBoxCount(30);
        request.setCid(1);
        
        //이사 짐 정보
        List<MoveItemRequest> moveItemList = new ArrayList<>();
        MoveItemRequest moveReq1= new MoveItemRequest();
        moveReq1.setFurnitureId(1);
        moveReq1.setOptionValId(1);
        moveReq1.setQuantity(1);
        moveItemList.add(moveReq1);
        
        MoveItemRequest moveReq2= new MoveItemRequest();
        moveReq2.setFurnitureId(2);
        moveReq2.setOptionValId(2);
        moveReq2.setQuantity(1);
        moveItemList.add(moveReq2);
        
        MoveItemRequest moveReq3= new MoveItemRequest();
        moveReq3.setFurnitureId(2);
        moveReq3.setOptionValId(5);
        moveReq3.setQuantity(1);
        moveItemList.add(moveReq3);
        
        MoveItemRequest moveReq4= new MoveItemRequest();
        moveReq4.setFurnitureId(2);
        moveReq4.setOptionValId(9);
        moveReq4.setQuantity(1);
        moveItemList.add(moveReq4);
        
        request.setMoveItemList(moveItemList);
        
        
    	ReqQuotationDTO dto = ReqQuotationDTO.builder()
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
    				.qty(mvReq.getQuantity())
    				.build();
    		dtoList.add(mvDto);
    	}
    	
		//when
        reqQuotationService.saveReqQuotation(dto, dtoList);
        
		//then
        ReqQuotationDTO reqDto = reqQuotationService.getReqQuotationByUser(request.getCustId());
        log.debug("저장 한 견적 -> {}", reqDto);
	}
	
	@Test
	@DisplayName("사용자별 견적 조회")
	void selectQuotationByuserTest() {
		//given
		String custId = "1";
		
		//when
		ReqQuotationDTO dto = reqQuotationService.getReqQuotationByUser(custId);
		
		//then
        log.debug("저장 한 견적 -> {}", dto);
	}
	
	@Test
	@DisplayName("견적서 전체 조회")
	void selectQuotaionListAllTest() {
		//given
		
		//when
		List<ReqQuotationDTO> list = reqQuotationService.getReqQuotationList();
		
		//then
		list.stream().forEach(q -> log.debug("저장 한 견적 => {}", q));
	}
	
	@Test
	@DisplayName("견적 상태 변경")
	void updateQuotaionStatusTest() {
		//given
		String reqQuotationId = "30473496-ef9f-443e-986e-70f0f20dc076"; 
		String status = "1";
		
		//when
		reqQuotationService.updateReqQuotationStatus(reqQuotationId, status);
		
		//then
		ReqQuotationDTO dto = reqQuotationService.getReqQuotationByUser("3");
	    log.debug("변경 된 상태 값 -> {}", dto.getStatus());
	}
	
	@Test
	@DisplayName("견적 수정")
	void modifyQuotationTest() throws Exception {
		
	    // Test data preparation
	    String strDate = "2024-11-05 00:00:00";
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date = sdf.parse(strDate);

	    // given
	    UpdateReqQuotationDTO updateDto = UpdateReqQuotationDTO.builder()
	            .pickupAddr("YangPeyng, Korea")
	            .deliveryAddr("Tokyo, Japan")
	            .moveDt(date)
	            .buildingType("단독주택")
	            .roomStructure("원룸")
	            .houseSize(18)
	            .hasElevator(false)
	            .boxCount(10)
	            .quotationAmount(BigDecimal.valueOf(1000.00))
	            .build();

	    String quotationId = "30473496-ef9f-443e-986e-70f0f20dc076";
	  
	    // when
	    reqQuotationService.modifyReqQuotation(updateDto, quotationId);

	    // then
	    ReqQuotationDTO dto = reqQuotationService.getReqQuotationByUser("3"); // Assuming user ID is "3"
	    log.debug("변경 된 값 -> {}", dto);

	}
	
	@Test
	@DisplayName("견적 수정 멀티스레드 테스트 스레드풀 5개, 요청 10개")
	void modifyQuotationMultiTest() throws Exception {
	    // Test data preparation
	    String strDate = "2024-11-05 00:00:00";
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date = sdf.parse(strDate);

	    // Update DTO preparation
	    UpdateReqQuotationDTO updateDto = UpdateReqQuotationDTO.builder()
	            .pickupAddr("YangPeyng, Korea")
	            .deliveryAddr("Tokyo, Japan")
	            .moveDt(date)
	            .buildingType("단독주택")
	            .roomStructure("원룸")
	            .houseSize(18)
	            .hasElevator(false)
	            .boxCount(10)
	            .quotationAmount(BigDecimal.valueOf(1000.00))
	            .build();

	    String quotationId = "30473496-ef9f-443e-986e-70f0f20dc076";

	    // ExecutorService를 사용하여 멀티스레드 환경에서 수정 요청을 처리
	    ExecutorService executorService = Executors.newFixedThreadPool(5); // 5개의 스레드
	    List<Future<Void>> futures = new ArrayList<>();

	    for (int i = 0; i < 10; i++) { // 10개의 수정 요청
	        final int attempt = i;
	        futures.add(executorService.submit(() -> {
	            try {
	                reqQuotationService.modifyReqQuotation(updateDto, quotationId);
	                log.debug("수정 성공: 요청 번호 {}", attempt);
	            } catch (Exception e) {
	                log.error("수정 실패: 요청 번호 {} - {}", attempt, e.getMessage());
	            }
	            return null;
	        }));
	    }

	    // 모든 작업이 완료될 때까지 대기
	    for (Future<Void> future : futures) {
	        try {
	            future.get(); // 예외를 체크하기 위해 get() 호출
	        } catch (ExecutionException e) {
	            log.error("스레드 실행 중 예외 발생: {}", e.getCause().getMessage());
	        }
	    }

	    executorService.shutdown(); // 스레드 풀 종료
	}
	
	@Test
	@DisplayName("견적 삭제")
	void deleteQuotaionTest() {
		//given
		String quotaionId = "30473496-ef9f-443e-986e-70f0f20dc076";
		
		//when
		reqQuotationService.deleteReqQuotation(quotaionId);
		
		//then
		
	}
}
