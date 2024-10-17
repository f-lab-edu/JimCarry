package com.study.jimcarry.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class UserEntity {
    
    // 사용자 아이디
    private String userId;
    
    // 사용자 이름
    private String userName;   
    
    // 암호화된 비밀번호
    private String password;     
    
    // 핸드폰 번호
    private String phoneNumber;   
    
    // 이메일
    private String email;        
    
    // 유저 유형
    private String userType;
}
