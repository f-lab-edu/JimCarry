package com.study.jimcarry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.study.jimcarry.security.FailureLoginHandler;
import com.study.jimcarry.security.SuccessLoginHandler;
import com.study.jimcarry.security.SuccessLogoutHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .headers(headersConfigurer ->headersConfigurer
        		.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
        		)
        .csrf(AbstractHttpConfigurer::disable)
        //.cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .authorizeHttpRequests(authorize -> authorize
        	    .requestMatchers("/api/auth/**").permitAll()
				.requestMatchers("/actuator/**").permitAll()
        	    //.requestMatchers("/static/**").permitAll()
                //.requestMatchers(AntPathRequestMatcher.antMatcher("/api/**")).authenticated()
                .anyRequest().permitAll()
                )
        	.formLogin(login -> login.loginPage("/login.html")
        		.usernameParameter("id")
    	        .passwordParameter("pw")
    	        .loginProcessingUrl("/api/loginProcess")
    	        .successHandler(new SuccessLoginHandler())
    	        .failureHandler(new FailureLoginHandler())
    	        .permitAll()
            )
        .logout(logout -> logout.logoutUrl("/api/logout")
        		.logoutSuccessHandler(new SuccessLogoutHandler())
    	        .clearAuthentication(true)
    	        .deleteCookies("SESSION", "AUTHORITY", "JSESSIONID")
    	        .invalidateHttpSession(true)
    	        .permitAll()
        		)
        .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        		)
    		;
        
        return http.build();
	}
}
