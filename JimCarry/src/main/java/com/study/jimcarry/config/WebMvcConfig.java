package com.study.jimcarry.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc //Spring MVC의 기본 구성을 활성화합니다. 컨트롤러, 뷰 리졸버 및 다양한 구성 설정 지원을 포함하여 웹 애플리케이션을 실행하는 데 필요한 여러 가지 주요 기능을 활성화
public class WebMvcConfig implements WebMvcConfigurer {
//    public void addInterceptors(InterceptorRegistry registry) {
//       	registry.addInterceptor(new WebMvcInterceptor())
//        		.addPathPatterns("/api/**");
//    }
}
