package com.study.jimcarry.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
	
    NOT_FOUND(HttpStatus.NOT_FOUND, "리소스를 찾을 수 없음"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없음"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "접근이 금지됨"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청"),
    CONFLICT(HttpStatus.CONFLICT, "이미 존재하는 자원입니다.");

    private final HttpStatus status;
    private final String message;

    private ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getCode() {
        return status.value(); 
    }

    public String getMessage() {
        return message;
    }
}
