package com.study.jimcarry.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class UserInfoDTO {
	
	/**
	 * 유저 아이디
	 */
	@Schema(name="userId", description = "유저 아이디")
	private String userId;
	
	/**
	 * 유저 이름
	 */
	@Schema(name="userName", description = "유저 이름")
	private String userName;
	
	/**
	 * 유저 암호
	 */
	@Schema(name="password", description = "유저 암호")
	private String password;
	
	/**
	 * 유저 현재 암호
	 */
	@Schema(name="currentPassword", description = "유저 현재 암호")
	private String currentPassword;
	
	/**
	 * email
	 */
	@Schema(name="email", description = "email")
	private String email;
	
	/**
	 * userType
	 */
	@Schema(name="userType", description = "userType")
	private String userType;
	
	/**
	 * phoneNumber
	 */
	@Schema(name="phoneNumber", description = "phoneNumber")
    private String phoneNumber;

	/**
	 * cid
	 */
	@Schema(name="cid", description = "cid")
	private int cid;
}
