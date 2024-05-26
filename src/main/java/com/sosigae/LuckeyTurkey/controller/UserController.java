package com.sosigae.LuckeyTurkey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sosigae.LuckeyTurkey.domain.User;
import com.sosigae.LuckeyTurkey.service.UserService;
 
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "/user/register";
    }
    
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        userService.registerMember(user);
        model.addAttribute("message", "회원가입이 완료되었습니다.");
        return "redirect:/user/login"; // 성공 시 로그인 페이지로 이동
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model) {
        try {
            User account = userService.loginMember(user.getUser_id(), user.getPassword());
            model.addAttribute("loginResult", "로그인 성공: " + account.getName());
            return "/search/search"; // 성공 시 메인 페이지로 이동 (잠시 search 페이지로 수정 !!!)
        } catch (IllegalArgumentException e) {
            model.addAttribute("loginResult", "유효하지 않은 사용자입니다.");
            return "/user/login"; // 실패 시 다시 로그인 페이지로 이동
        }
    }
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "/user/login"; // 로그인 페이지로 이동
    }
}