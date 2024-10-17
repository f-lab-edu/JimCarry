package com.study.jimcarry.domain;

import com.study.jimcarry.domain.ReqQuotationEntity.ReqQuotationEntityBuilder;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoleEntity {
	
	// 권한 ID
    private Integer roleId;     
    
    // 권한 이름
    private String roleName;    
    
    // 권한 설명
    private String description;   
    
	public RoleEntityBuilder toBuilder() {
	    return RoleEntity.builder()
	            .roleId(this.roleId)
	            .roleName(this.roleName)
	            .description(this.description);
	}
}
