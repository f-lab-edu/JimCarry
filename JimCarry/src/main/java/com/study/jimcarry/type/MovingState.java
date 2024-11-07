package com.study.jimcarry.type;

import com.study.jimcarry.exception.CustomException;
import com.study.jimcarry.exception.ErrorCode;
import lombok.Getter;
import org.apache.commons.codec.binary.StringUtils;

@Getter
public enum MovingState {
    BEFORE_MOVE("0", "이사전"),
    AFTER_MOVE("1", "이사완료")
    ;

    private final String code;
    private final String description;

    MovingState(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
