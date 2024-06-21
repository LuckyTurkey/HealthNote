package com.sosigae.LuckeyTurkey.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sosigae.LuckeyTurkey.dto.MedicalRecordDTO;
import com.sosigae.LuckeyTurkey.service.MedicalRecordService;

@Controller
public class MedicalRecordController {
	
	@Autowired
    private MedicalRecordService medicalRecordService;

	@GetMapping("/records")
    public String getMedicalRecords(HttpSession session, Model model) {
		int userId = (int) session.getAttribute("user_id");
		    if (userId == -1) {
		        // 세션에 사용자 ID가 없으면 로그인 페이지로 리다이렉트 또는 예외 처리
		        return "redirect:/user/login";
		    }
        
        // 사용자 ID를 기반으로 진료 기록을 조회
        List<MedicalRecordDTO> medicalRecords = medicalRecordService.getMedicalRecordsByUserId(userId);
        
        
        // 조회된 진료 기록을 모델에 추가하여 View로 전달
        System.out.println(userId);
        System.out.println(medicalRecords);
        model.addAttribute("medicalRecords", medicalRecords);
        
        return "records/records";
    }
	
	
}
