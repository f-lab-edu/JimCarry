package com.study.jimcarry.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration// Spring IoC(Inversion of Control) 컨테이너에서 관리할 빈을 정의하는 데 사용
public class AppConfig {

    @Bean // Spring 컨테이너에서 관리할 빈을 생성함.
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
