package com.sosigae.LuckeyTurkey.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sosigae.LuckeyTurkey.domain.MedicalRecord;
import com.sosigae.LuckeyTurkey.domain.User;
import com.sosigae.LuckeyTurkey.domain.VaccinationRecord;
import com.sosigae.LuckeyTurkey.dto.MedicalRecordDTO;
import com.sosigae.LuckeyTurkey.service.MedicalRecordService;
import com.sosigae.LuckeyTurkey.service.UserService;

@Controller
public class MedicalRecordController {
	
	@Autowired
    private MedicalRecordService medicalRecordService;

	@Autowired
    private UserService userService;
	
	 @GetMapping("/records")
	    public String getMedicalRecords(HttpSession session, Model model) {
	        try {
	            // 세션에서 id 가져오기
	            String id = (String) session.getAttribute("id");

	            if (id == null) {
	                return "redirect:/user/selectLogin";
	            }

	            User user = userService.findUserById(id);
	            if (user == null) {
	                throw new IllegalArgumentException("User not found for id: " + id);
	            }

	            // User 객체에서 userId 가져오기
	            int userId = user.getUserId();

	            // userId를 기반으로 진료 기록을 조회
	            List<MedicalRecord> medicalRecords = medicalRecordService.getMedicalRecordsWithHospitalInfoByUserId(userId);

	            model.addAttribute("medicalRecords", medicalRecords);
	            model.addAttribute("userId", userId);

	            return "records/records";
	        } catch (Exception e) {
	            e.printStackTrace();

	            model.addAttribute("error", "Error fetching medical records: " + e.getMessage());
	            return "error";
	        }
	 }

}
