package com.in28minutes.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 정적으로 import 선언하면 앞에 클래스(아래는 Customizer) 없이도 사용 가능하다.
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 1. 모든 요청은 인증을 받도록
        http.authorizeHttpRequests(
            auth -> auth.anyRequest().authenticated()
        );
        // 2. 만약 인증된 상태라면 웹 페이지가 보이도록
        http.httpBasic(withDefaults());
        // 3. CSRF 해제 -> POST, PUT 요청을 받을 수 있도록
        http.csrf().disable();

        return http.build();
    }
}
