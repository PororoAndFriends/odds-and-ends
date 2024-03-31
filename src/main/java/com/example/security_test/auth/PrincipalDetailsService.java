package com.example.security_test.auth;

import com.example.security_test.domain.User;
import com.example.security_test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// /login 요청(loginProcessingUrl 설정된 주소) 이 오면, UserDetailsService 설정되어 있는 메서드 실행
// 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어짐
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    // form 데이터가 넘어올 때 username값을 받아옴.(input 테그의 이름은 무조건 username이어야 함)
    // 세션 <- Authentication <- UserDetails
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            return new PrincipalDetails(user);
        }
        return null;
    }
}
