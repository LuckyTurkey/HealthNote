package com.sosigae.LuckeyTurkey.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sosigae.LuckeyTurkey.domain.Doctor;
import com.sosigae.LuckeyTurkey.domain.Hospital;
import com.sosigae.LuckeyTurkey.domain.MedicalRecord;
import com.sosigae.LuckeyTurkey.service.DoctorService;
import com.sosigae.LuckeyTurkey.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sosigae.LuckeyTurkey.domain.User;
import com.sosigae.LuckeyTurkey.dto.MedicalRecordDTO;
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

    @GetMapping("/adminRegister")
    public String showAdminRegisterForm(Model model) {
        model.addAttribute("hospital", new Hospital());
        return "user/adminRegister";
    }
    @GetMapping("/doctorRegister")
    public String showDoctorRegisterForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "user/doctorRegister";
    }
    @GetMapping("/patientRegister")
    public String showPatientRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "user/patientRegister";
    }

    @GetMapping("/patientMain")
    public String showPatientMain(HttpSession session) {
        System.out.println(session.getAttribute("is_admin"));

        return "main/patientMain";
    }

    @GetMapping("/doctorMain")
    public String showDoctortMain(HttpSession session) {
        System.out.println(session.getAttribute("is_admin"));
        return "main/doctorMain";
    }

    @GetMapping("/hospitalMain")
    public String showHospitaltMain(HttpSession session) {
        System.out.println(session.getAttribute("is_admin"));
        return "main/hospitalMain";
    }

    
    @PostMapping("/adminRegister")
    public String registerAdmin(@ModelAttribute Hospital hospital, RedirectAttributes redirectAttributes) {
        try {           
        	hospital.setIs_admin(1);
            hospitalService.registerHospital(hospital);
            redirectAttributes.addFlashAttribute("message", "관리자 회원가입이 완료되었습니다.");
            return "redirect:/user/selectLogin"; 
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "회원가입 중 오류가 발생하였습니다.");
            return "redirect:/user/adminRegister";
        }
    }

    @PostMapping("/doctorRegister")
    public String registerDoctor(@ModelAttribute Doctor doctor, @RequestParam("hospitalLoginId") String hospitalLoginId,
                                 RedirectAttributes redirectAttributes) {
        Hospital hospital = hospitalService.findHospitalByLoginId(hospitalLoginId);
        System.out.println("병원 이름 : " + hospital.getName() + " 병원 pk : " + hospital.getHospitalId());
        try {
            doctor.setHospitalId(hospital.getHospitalId());
            doctor.setIs_admin(2);
            // 디버깅 출력
            System.out.println("저장 전 Doctor 객체: " + doctor);

            doctorService.registerDoctor(doctor);
            redirectAttributes.addFlashAttribute("message", "의사 회원가입이 완료되었습니다.");
            return "redirect:/user/selectLogin";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "회원가입 중 오류가 발생하였습니다.");
            System.out.println("오류1");
            return "redirect:/user/doctorRegister";
        }
    }


    @PostMapping("/patientRegister")
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.registerUser(user);

            redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다.");
            return "redirect:/user/selectLogin"; 
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "회원가입 중 오류가 발생하였습니다.");
            return "redirect:/user/patientRegister";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "user/login";
    }

    @GetMapping("/selectLogin")
    public String showSelectLoginPage() {
        return "user/selectLogin"; 
    }
    
    @GetMapping("/adminLogin")
    public String adminLoginPage(Model model) {
    	model.addAttribute("hospital", new Hospital());
        return "user/adminLogin"; 
    }
    @GetMapping("/patientLogin")
    public String patientLoginPage(Model model) {
    	model.addAttribute("user", new User());
        return "user/patientLogin"; 
    }
    @GetMapping("/doctorLogin")
    public String doctorLoginPage(Model model) {
    	model.addAttribute("doctor", new Doctor());
        return "user/doctorLogin"; 
    }
    
    @PostMapping("/selectLogin")
    public String selectLogin(@RequestParam(name = "is_admin") int isAdmin) {
        if (isAdmin == 1) {
            return "user/adminLogin"; 
        } else if (isAdmin == 2) {
            return "user/doctorLogin"; 
        } else if (isAdmin == 3) {
            return "user/patientLogin"; 
        } else {
            return "user/login"; 
        }
    }
    
    @PostMapping("/patientLogin")
    public String patientLogin(@ModelAttribute User user, Model model, HttpSession session) {
        try {
            User account = userService.loginMember(user.getId(), user.getPassword(), user.getIs_admin());


            if (account == null) {
                model.addAttribute("loginResult", "로그인 실패: 사용자를 찾을 수 없습니다.");
                return "user/patientLogin";
            }

            session.setAttribute("id", account.getId());
            session.setAttribute("user_id", account.getUserId());
            session.setAttribute("is_admin", account.getIs_admin());

            System.out.println("로그인 유저 : : " + user.getId() + " " + account.getUserId() + " is_admin : " + account.getIs_admin());

            model.addAttribute("loginResult", "로그인 성공: " + account.getName());
            return "redirect:/user/patientMain"; // 환자 메인 페이지로 리다이렉트
//            return "main/patientMain"; // 환자 메인 페이지로 이동

        } catch (IllegalArgumentException e) {
            model.addAttribute("loginResult", "유효하지 않은 사용자입니다.");
            return "user/patientLogin";
        }
    }
    
    @PostMapping("/adminLogin")
    public String hospitalLogin(@ModelAttribute Hospital hospital, Model model, HttpSession session) {
        try {
            Hospital account = hospitalService.loginHospital(hospital.getId(), hospital.getPassword());
            
            if (account == null) {
                model.addAttribute("loginResult", "로그인 실패: 사용자를 찾을 수 없습니다.");
                return "user/adminLogin";
            }

            session.setAttribute("hospitalId", account.getId());
            session.setAttribute("hospital", account.getHospitalId());
            session.setAttribute("is_admin", account.getIs_admin());

            System.out.println("로그인 유저 : : " + hospital.getId() + " " + hospital.getHospitalId() + " is_admin : " + account.getIs_admin());

            model.addAttribute("loginResult", "로그인 성공: " + account.getName());
            return "redirect:/user/hospitalMain"; // 관리자 메인 페이지로 리다이렉트
//            return "main/hospitalMain"; // 관리자 메인 페이지로 이동

        } catch (IllegalArgumentException e) {
            model.addAttribute("loginResult", "유효하지 않은 사용자입니다.");
            return "user/adminLogin";
        }
    }
    
    @PostMapping("/doctorLogin")
    public String doctorLogin(@ModelAttribute Doctor doctor, Model model, HttpSession session) {
        try {
            Doctor account = doctorService.loginDoctor(doctor.getId(), doctor.getPassword());
            
            if (account == null) {
                model.addAttribute("loginResult", "로그인 실패: 사용자를 찾을 수 없습니다.");
                return "user/doctorLogin";
            }

            session.setAttribute("doctorId", account.getId());
            session.setAttribute("doctor", account.getDoctorId());
            session.setAttribute("is_admin", account.getIs_admin());

            System.out.println("로그인 유저 : : " + account.getId() + " " + account.getHospitalId() + " is_admin : " + account.getIs_admin());
            model.addAttribute("loginResult", "로그인 성공: " + account.getName());
            return "redirect:/user/doctorMain"; // 의사 메인 페이지로 리다이렉트
//            return "main/doctorMain"; // 의사 메인 페이지로 이동

        } catch (IllegalArgumentException e) {
            model.addAttribute("loginResult", "유효하지 않은 사용자입니다.");
            return "user/doctorLogin";
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
    

    @GetMapping("/details")
    public ResponseEntity<?> getUserDetails(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("user_id");
        
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }

        List<MedicalRecord> medicalRecords = userService.getMedicalRecords(userId);
        return ResponseEntity.ok(medicalRecords);
    }
    
    
   
    
}
