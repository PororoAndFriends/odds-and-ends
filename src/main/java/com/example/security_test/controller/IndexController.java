package com.example.security_test.controller;

import com.example.security_test.domain.User;
import com.example.security_test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public String user(){
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






}
