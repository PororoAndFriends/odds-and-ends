package com.example.security_test.oauth;

import com.example.security_test.auth.PrincipalDetails;
import com.example.security_test.auth.provider.FaceBookUserInfo;
import com.example.security_test.auth.provider.GoogleUserInfo;
import com.example.security_test.auth.provider.NaverUserInfo;
import com.example.security_test.auth.provider.OAuth2Userinfo;
import com.example.security_test.domain.User;
import com.example.security_test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

// OAuth 기본 설정 서비스
@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 구글로부터 받은 userRequest 데이터 후처리. 엑세스토큰과 프로필에 대한 정보 등을 모두 처리할 수 있음
    // 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어짐
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest = " + userRequest);
        // 어느 계정으로 로그인했는지 확인 가능
        System.out.println("userRequest.getClientRegistration().getRegistrationId() = " + userRequest.getClientRegistration().getRegistrationId());
        // 구글 로그인 -> code return -> AccessToken 요청
        System.out.println("userRequest.getAccessToken().getTokenValue() = " + userRequest.getAccessToken().getTokenValue());

        // AccessToken을 발급받고 loadUser함수를 통해 회원 프로필 정보를 가져올 수 있음
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("super.loadUser(userRequest).getAttributes() = " + oAuth2User.getAttributes());

        System.out.println("userRequest.getAdditionalParameters() = " + userRequest.getAdditionalParameters());

        // 이후로는 후처리 코드(강제? 회원가입)

        OAuth2Userinfo oAuth2Userinfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            oAuth2Userinfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if ((userRequest.getClientRegistration().getRegistrationId().equals("google"))) {
            oAuth2Userinfo = new FaceBookUserInfo(oAuth2User.getAttributes());
        } else if ((userRequest.getClientRegistration().getRegistrationId().equals("naver"))) {
            oAuth2Userinfo = new NaverUserInfo((Map<String, Object>) oAuth2User.getAttributes().get("response"));
        } else   log.error("not supporting RegistrationId");

        String provider = oAuth2Userinfo.getProvider();
        String providerId = oAuth2Userinfo.getProviderId();
        String username = provider + providerId;
        String email = (String) oAuth2User.getAttributes().get("email");
        // 비밀번호는 크게 의미는 없음
        String password =encoder.encode("뽀롱뽀롱 뽀로로");
        String role = "ROLE_USER";

        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User(username, password, oAuth2Userinfo.getEmail(), role, provider, providerId);
            userRepository.save(user);
        }

        // 리턴 타입을 PrincipalDetails로 해줌으로써 시큐리티 세션에 PrincipalDetails타입으로 넣어줌
        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }
}
