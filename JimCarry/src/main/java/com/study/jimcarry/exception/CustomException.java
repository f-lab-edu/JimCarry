package com.study.jimcarry.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class CustomException extends RuntimeException {

	//private static final long serialVersionUID = 1L;
		
	private final int code;

	public CustomException(int code, String msg){
		super(msg);
		this.code = code;
	}
	
	public CustomException(String msg){
//		super(msg);
//		this.code = 200;
		this(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
	}
	
	public CustomException(ErrorCode errCode) {
		this(errCode.getCode(), errCode.getMessage());
	}

	public int getCode() {
		return code;
	}
}
