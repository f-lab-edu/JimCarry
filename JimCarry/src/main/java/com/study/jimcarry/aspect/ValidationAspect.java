package com.study.jimcarry.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.study.jimcarry.annotation.ValidateFields;
import com.study.jimcarry.service.ValidationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
@RequiredArgsConstructor
public class ValidationAspect {

    private final ValidationService validationService;

    @Before("@annotation(com.study.jimcarry.annotation.ValidateFields)")
    public void validateFields(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ValidateFields validateFields = method.getAnnotation(ValidateFields.class);
    
        if (validateFields != null) {
   
            // 필드 목록을 가져옵니다.
            String[] fields = validateFields.value();

            // 첫 번째 파라미터가 요청 객체라고 가정합니다.
            Object request = joinPoint.getArgs()[0];
            log.info("Validating fields: {}", (Object) fields); // 추가된 로그
            log.info("Request object: {}", request); // 추가된 로그
            // ValidationService를 사용하여 유효성 검사 수행
            validationService.validateRequest(request, fields);
        }
    }
}
