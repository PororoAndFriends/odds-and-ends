package com.example.security_test.controller;

import com.example.security_test.auth.PrincipalDetails;
import com.example.security_test.domain.User;
import com.example.security_test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @ResponseBody
    @GetMapping("/user")
    public String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("principalDetails = " + principalDetails.getUser());
        return "user";
    }

    @ResponseBody
    @GetMapping("/manager")
    public String manager(){
        return "manager";
    }

    @ResponseBody
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/loginForm")
    public String login(){
        return "loginForm";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute User user){
        user.setRole("user");
        String encodePwd = encoder.encode(user.getPassword());
        user.setPassword(encodePwd);

        userRepository.save(user);
        return "redirect:/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @ResponseBody
    @GetMapping("/info")
    public String info(){
        return "info";
    }

    @GetMapping("/test/login")
    @ResponseBody
    public String testLogin(Authentication authentication,
                            //@AuthenticationPrincipal를 통해 세션 정보를 얻어올 수 있음
                            @AuthenticationPrincipal PrincipalDetails userDetails) {
        PrincipalDetails principal = (PrincipalDetails)authentication.getPrincipal();
        System.out.println("authentication.getPrincipal()).getUser() = " + principal.getUser());
        System.out.println("userDetails.getUsername() = " + userDetails.getUser());
        return "세션정보 확인";
    }

    @GetMapping("/test/oauth/login")
    @ResponseBody
    public String testOAuthLogin(Authentication authentication,
                                 @AuthenticationPrincipal OAuth2User oAuth2User) {
        // OAuth로 로그인하게 되면 UserDetails를 상속받지 않기 때문에 OAuth2User를 통해 받아줘야 함
        OAuth2User oAuth2User2 = (OAuth2User)authentication.getPrincipal();
        // .getAttributes()는 PrincipalOauth2UserService의 super.loadUser(userRequest).getAttributes()에서 나온 것임
        System.out.println("oAuth2User.getAttributes() = " + oAuth2User2.getAttributes());
        System.out.println("temp.getAttributes() = " + oAuth2User.getAttributes());

        /*
        스프링 스큐리티에는 시큐리티만의 세션이 따로 있음
        이 세션 안에는 Authentication 타입만 들어갈 수 있음
        이 Authentication 안에는 UserDetails와 OAuth2User 2가지만 들어갈 수 있음

        일반적인 로그인 -> userDetails
        OAuth 로그인 -> OAuth2User

        따라서 불편하기 때문에 두 타입을 합쳐주는 것이 좋음 -> PrincipalDetails 생성
         */
        return " OAuth 세션정보 확인";
    }

}
