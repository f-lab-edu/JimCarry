package com.study.jimcarry.exception;

public enum ErrorCode {
    NOT_FOUND(404, "리소스를 찾을 수 없음"),
    UNAUTHORIZED(401, "권한이 없음"),
    FORBIDDEN(403, "접근이 금지됨"),
    INTERNAL_SERVER_ERROR(500, "내부 서버 오류"),
    BAD_REQUEST(400, "잘못된 요청");

	private int code;

	private String message;

	private ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
