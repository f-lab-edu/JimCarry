package com.study.jimcarry.type;

import com.study.jimcarry.exception.CustomException;
import com.study.jimcarry.exception.ErrorCode;

public enum QuotationStatus {
    DRAFT(0, "견적작성"),
    CONFIRMED(1, "견적확정"),
    SELECTED(2, "견적채택");

    private final int code;
    private final String description;

    QuotationStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static QuotationStatus fromCode(int code) {
        for (QuotationStatus status : QuotationStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }

    public void validDraft() {
        if (this != DRAFT) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "이미 확정되거나 채택 된 견적 입니다.");
        }
    }
}
