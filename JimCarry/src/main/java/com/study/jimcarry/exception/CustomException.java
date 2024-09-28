package com.study.jimcarry.exception;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
		
	int code;

	public CustomException(String msg){
		super(msg);
		this.code = 200;
	}

	public CustomException(int code, String msg){
		super(msg);
		this.code = code;
	}

	public CustomException(ErrorCode errCode) {
		super(errCode.getMessage());
		this.code = errCode.getCode();
	}

	public int getCode() {
		return code;
	}
}
