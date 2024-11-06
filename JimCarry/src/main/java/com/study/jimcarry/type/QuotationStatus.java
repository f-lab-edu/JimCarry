package com.study.jimcarry.type;

import com.study.jimcarry.exception.CustomException;
import com.study.jimcarry.exception.ErrorCode;
import lombok.Getter;
import org.apache.commons.codec.binary.StringUtils;

import java.util.Objects;

@Getter
public enum QuotationStatus {
    DRAFT("0", "견적작성"),
    CONFIRMED("1", "견적확정"),
    SELECTED("2", "견적채택");

    private final String code;
    private final String description;

    QuotationStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    //견적확정 되었거나 채택된 경우 예외 발생
    public static void validDraftStatus(String currentStatus) {
        if (!StringUtils.equals(DRAFT.getCode(), currentStatus)) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "이미 확정되거나 채택 된 견적 입니다.");
        }
    }

    //채택된 경우 예외 발생
    public static void validSelectedStatus(String currentStatus) {
        if (StringUtils.equals(SELECTED.getCode(), currentStatus)) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "이미 채택 된 견적 입니다.");
        }
    }
}
