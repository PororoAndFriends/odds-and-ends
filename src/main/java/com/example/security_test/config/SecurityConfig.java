package com.example.security_test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Security filter가 spring filterChain에 등록됨
@EnableMethodSecurity(securedEnabled = true) // secured 어노테이션 활성화,
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 인가(접근권한) 설정
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/user/**").authenticated()
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/manager/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
                .anyRequest().permitAll()
        )
                .formLogin(form -> form.loginPage("/loginForm")
                        .loginProcessingUrl("/login") // /login이 호출되면 Security가 낚아채서 로그인해줌
                        .defaultSuccessUrl("/")
                );

        // 사이트 위변조 요청 방지
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
