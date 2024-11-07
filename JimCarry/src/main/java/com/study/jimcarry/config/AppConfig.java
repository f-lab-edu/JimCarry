package com.study.jimcarry.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Configuration// Spring IoC(Inversion of Control) 컨테이너에서 관리할 빈을 정의하는 데 사용
public class AppConfig {

	  /**
	   * ModelMapper 대신 @Builer 패턴으로 사용.
	   * @return
	   */
    @Bean // Spring 컨테이너에서 관리할 빈을 생성함.
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
