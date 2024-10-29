package com.study.jimcarry.api;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuotationAcceptRequest {
	
	//견적요청 번호
	@JsonProperty("quotationReqNo") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="quotationReqNo", description="견적요청 번호") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="견적요청 번호는 필수입니다.")
	private String quotationReqNo;
	
	//견적채택일시
	@JsonProperty("acceptDt") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="acceptDt", description="견적채택일시") //Swagger/OpenAPI 문서를 자동 생성
	@NotNull(message="견적 채택 일시는 필수입니다.")
	private LocalDateTime acceptDt;
	
	//고객 아이디
	@JsonProperty("custId") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="custId", description="고객 아이디") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="고객 ID는 필수입니다.")
	private String custId;
	
	//기사님 아이디
	@JsonProperty("driverId") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="driverId", description="기사님 아이디") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="기사님 ID는 필수입니다.")
	private String driverId;

	//이사상태
	/**
	 * InProgress(이사중), Complete(이사완료)
	 */
	@JsonProperty("movingState") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="movingState", description="이사상태") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="이사상태는 필수입니다.")
	private String movingState;
	
    // 생성자
	@JsonProperty("cid") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="cid", description="생성자") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="생성자는 필수입니다.")
    private Integer cid;
}
