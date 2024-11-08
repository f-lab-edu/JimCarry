package com.study.jimcarry.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

//@Data 
/**
* @Data는 Lombok에서 @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor를 한 번에 적용하는 어노테이션
* 하지만 @Builder를 사용할 때 @Data와 같이 사용할 경우, @Builder가 자동으로 만들어주는 생성자와 충돌할 수 있습니다.
* 모든 필드에 대한 setter가 생성되기 때문에 빌더 패턴의 사용이 필요 없어 보이는 문제가 있을 수 있습니다.
*/
@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // null 제외
public class QuotationAcceptDTO {
	
	// 견적요청 번호
	@Schema(name = "quotationReqNo", description = "견적요청 번호")
	private String quotationReqNo;

	// 견적채택일시
	@Schema(name = "acceptDt", description = "견적채택일시")
	private LocalDateTime acceptDt;

	// 고객 아이디
	@Schema(name = "custId", description = "고객 아이디")
	private String custId;

	// 기사님 아이디
	@Schema(name = "driverId", description = "기사님 아이디")
	private String driverId;
	
	// 이사상태
	@Schema(name = "movingState", description = "이사 상태")
	private String movingState;
	
	// 생성자
	@Schema(name = "cid", description = "생성자")
	private Integer cid;
}
