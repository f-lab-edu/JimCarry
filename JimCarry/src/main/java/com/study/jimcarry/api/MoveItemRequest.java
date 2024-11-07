package com.study.jimcarry.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MoveItemRequest {
	
	//고객 아이디
	@JsonProperty("furnitureId") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="furnitureId", description="가구 ID") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="가구 ID는 필수입니다.")
	private Integer furnitureId;
	
    // 출발지 주소
	@JsonProperty("optionValId") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="optionValId", description="옵션 명 ID") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="옵션 명 ID는 필수 입니다.")
    private Integer optionValId;

	// 가구 수량
	@JsonProperty("qty") //클라이언트에서 requestBody에 json으로 보낼 때 매핑
	@Schema(name="qty", description="가구 수량") //Swagger/OpenAPI 문서를 자동 생성
	@NotBlank(message="가구 수량은 필수 입니다.")
    private Integer quantity;
}
