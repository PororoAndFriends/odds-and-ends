package com.example.security_test.auth;

// Security가 /login을 낚아채서 로그인 진행 -> 로그인 완료시 Security 세션에 넣어줌(Security ContextHolder)
// 세션에 들어갈 수 있는 객체 : Authentication 객체
// Authentication 객체 안에 들어갈 수 있는 객체 :  UserDetails 객체

// 결론 : SecuritySession(Authentication(UserDetails)))

import com.example.security_test.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/*
        스프링 스큐리티에는 시큐리티만의 세션이 따로 있음
        이 세션 안에는 Authentication 타입만 들어갈 수 있음
        이 Authentication 안에는 UserDetails와 OAuth2User 2가지만 들어갈 수 있음

        일반적인 로그인 -> userDetails
        OAuth 로그인 -> OAuth2User

        따라서 불편하기 때문에 두 타입을 합쳐주는 것이 좋음 -> PrincipalDetails 생성
         */

@RequiredArgsConstructor
@Getter
@Setter
public class PrincipalDetails implements UserDetails, OAuth2User {

    private final User user;
    private Map<String, Object> attributes;

    // OAuth 로그인시 사용할 생성자
    public PrincipalDetails(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    // 중요하진 않음. 보통 null로 두는 경우가 많음(안씀)
    @Override
    public String getName() {
        // return sub(외부 계정의 id같은 종목)
        return (String)attributes.get("sub");
    }
}
