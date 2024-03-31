package com.example.security_test.config;

import com.example.security_test.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

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
                )
                // OAuth 로그인 페이지 지정
                .oauth2Login(oauth -> oauth.loginPage("/loginForm")
                        // 후처리는 principalOauth2UserService의 loadUser 함수에 맡김
                        // 코드가 아닌 엑세토큰과 프로필정보에 대한 후처리가 필요함
                        .userInfoEndpoint(endPoint -> endPoint.userService(principalOauth2UserService))
                );

        // 사이트 위변조 요청 방지
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
