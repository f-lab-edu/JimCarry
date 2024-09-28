package com.study.jimcarry.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	/**
	 * API 명세
	 * @return
	 */
    @Bean
    OpenAPI getOpenApi() {

        return new OpenAPI().components(new Components())
            .info(new Info()
            		.title("JimCarry")
                    .version("1.0.0")
                    .description("JimCarry API DOC")); 
    }
    
    @Bean
    GroupedOpenApi getReqQuotationApi() {

        return GroupedOpenApi
            .builder()
            .group("ReqQuotation")
            .pathsToMatch("/api/req-quotation/**")
            .packagesToScan("com.study.jimcarry")
            .build();
    }
    
    @Bean
    GroupedOpenApi getConfirmQuotationApi() {

        return GroupedOpenApi
            .builder()
            .group("ConfirmQuotation")
            .pathsToMatch("/api/confirm-quotation/**")
            .packagesToScan("com.study.jimcarry")
            .build();
    }

}
