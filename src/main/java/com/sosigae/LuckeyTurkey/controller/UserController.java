package com.sosigae.LuckeyTurkey.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sosigae.LuckeyTurkey.domain.User;
import com.sosigae.LuckeyTurkey.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        userService.registerMember(user);
        model.addAttribute("message", "회원가입이 완료되었습니다.");
        return "login"; // 성공 시 로그인 페이지로 이동
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("user_id") String user_id,
                            @RequestParam("password") String password,
                            Model model) {
        try {
            User account = userService.loginMember(user_id, password);
            model.addAttribute("loginResult", "로그인 성공: " + account.getName());
            return "index"; // 성공 시 메인 페이지로 이동
        } catch (IllegalArgumentException e) {
            model.addAttribute("loginResult", "유효하지 않은 사용자입니다.");
            return "login"; // 실패 시 다시 로그인 페이지로 이동
        }
    }
}