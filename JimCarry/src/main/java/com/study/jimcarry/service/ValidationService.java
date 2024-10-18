package com.study.jimcarry.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.study.jimcarry.exception.CustomException;
import com.study.jimcarry.exception.ErrorCode;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidationService {
	
    private final Validator validator;

    public void validateRequest(Object request, String[] fields) {
        for (String field : fields) {
            Set<ConstraintViolation<Object>> violations = validator.validateProperty(request, field);
            if (!violations.isEmpty()) {
                ConstraintViolation<Object> violation = violations.iterator().next();
                throw new CustomException(ErrorCode.BAD_REQUEST.getCode(), violation.getMessage());
            }
        }
    }
}
