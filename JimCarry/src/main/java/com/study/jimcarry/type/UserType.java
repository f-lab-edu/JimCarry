package com.study.jimcarry.type;

public enum UserType {
	
    ADMIN("ROLE_ADMIN", "Admin"),
    USER("ROLE_USER", "User"),
    DRIVER("ROLE_DRIVER", "Driver");
	
	private String code;
	
	private String name;

    UserType(String code, String name) {
    	this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }
	
    public String getName() {
        return name;
    }
}
