package com.sosigae.LuckeyTurkey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/main/patientMain")
    public String patientMain() {
        return "main/patientMain";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/user/selectLogin";
    }
}
