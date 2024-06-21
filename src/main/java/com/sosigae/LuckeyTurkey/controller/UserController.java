package com.sosigae.LuckeyTurkey.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sosigae.LuckeyTurkey.domain.Doctor;
import com.sosigae.LuckeyTurkey.domain.Hospital;
import com.sosigae.LuckeyTurkey.service.DoctorService;
import com.sosigae.LuckeyTurkey.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sosigae.LuckeyTurkey.domain.User;
import com.sosigae.LuckeyTurkey.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
    	userService.registerMember(user);
        redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다.");
        return "user/login"; // 성공 시 로그인 페이지로 이동
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "user/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, HttpSession session) {
        try {
            User account = userService.loginMember(user.getId(), user.getPassword(), user.getIs_admin());

            int is_admin = userService.getUserIsAdmin(user.getId());
            
            session.setAttribute("id", account.getId());
            session.setAttribute("user_id", account.getUserId());
            
            model.addAttribute("loginResult", "로그인 성공: " + account.getName());
            
            if (is_admin == 1) {
                Hospital hospital = hospitalService.getHospitalById(account.getId());
                System.out.println("hospital 확인 : " + hospital.getHospitalId());
                session.setAttribute("hospitalId", hospital.getHospitalId());

                return "main/hospitalMain"; // 병원 메인
            }
            else if (is_admin == 2) {
                Doctor doctor = doctorService.getDoctorById(account.getId());
                session.setAttribute("doctorId", doctor.getDoctorId());

                return "main/doctorMain"; // 의사 메인
            }
            else if (is_admin == 3) {
            	return "main/patientMain"; // 환자 메인
            }
            else {
            	return "user/login";
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("loginResult", "유효하지 않은 사용자입니다.");
            return "user/login"; // 실패 시 다시 로그인 페이지로 이동
        }
    }
    
    @GetMapping("/logout")
    public String logoutUser(HttpServletRequest request, HttpServletResponse response) {
        // 현재 세션
        HttpSession session = request.getSession(false);
        
        // 세션이 존재하면 세션을 무효화 함
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/user/login";
    }
    
    
}
