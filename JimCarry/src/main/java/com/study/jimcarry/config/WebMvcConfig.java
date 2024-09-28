package com.study.jimcarry.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
//    public void addInterceptors(InterceptorRegistry registry) {
//       	registry.addInterceptor(new WebMvcInterceptor())
//        		.addPathPatterns("/api/**");
//    }
}
