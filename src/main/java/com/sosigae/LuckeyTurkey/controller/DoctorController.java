package com.sosigae.LuckeyTurkey.controller;

import com.sosigae.LuckeyTurkey.domain.Doctor;
import com.sosigae.LuckeyTurkey.domain.MedicalRecord;
import com.sosigae.LuckeyTurkey.domain.User;
import com.sosigae.LuckeyTurkey.service.DoctorService;
import com.sosigae.LuckeyTurkey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private UserService userService;


    // 의사 상세 조회
    @GetMapping("/doctor/{doctorId}")
    public String getDoctorDetail(@PathVariable int doctorId, Model model) {
        Doctor doctor = doctorService.getDocInfo(doctorId);
        model.addAttribute("doctor", doctor);
        return "/doctor/detail";
    }

    // 진료 기록 전체 리스트 조회
    @GetMapping("/doctor/{doctorId}/medRecordList")
    public String getMedRecordList(@PathVariable int doctorId, Model model) {
        List<MedicalRecord> medRecList = doctorService.getMedRecordList(doctorId);
        model.addAttribute("medRecList", medRecList);
        return "/doctor/medRecordList";
    }

    // 진료 기록 작성 페이지 출력
    @GetMapping("/newMedRec")
    public String showMedRecForm(Model model) {
//        int doctorId = (int) session.getAttribute("doctorId");
//        System.out.println( doctorId) ;
        System.out.println( "화면 출력 성공") ;
        model.addAttribute("medicalRecord", new MedicalRecord());
        return "/doctor/addMedRecord";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    // 진료 기록 작성
    @PostMapping("/submitMedicalRecord")
    public String submitMedicalRecord(@Valid @ModelAttribute("medicalRecord") MedicalRecord medicalRecord,
                                      BindingResult result, Model model,
                                      HttpSession session) {
        System.out.println( "시작");
        if (result.hasErrors()) {
            System.out.println( "오류 있음");
            return "/doctor/addMedRecord";
        }
        System.out.println( "오류는 없음");
        System.out.println(session.getAttribute("doctorId"));

        // 주민등록번호로 사용자 찾기
        User user = userService.findUserByPersonalCode(medicalRecord.getPersonal_code());

        // hospitalId set
        Doctor doctor = doctorService.getDoctorById((String) session.getAttribute("doctorId"));
        medicalRecord.setHospitalId(doctor.getHospitalId());

        // 사용자가 존재하는 경우
        if (user != null) {
            medicalRecord.setUserId(user.getUserId());
            System.out.println("set userId : " + medicalRecord.getUserId()) ;

            System.out.println("---- 뭐가 문제인지 확인 ----") ;
            int doctorIdStr = medicalRecord.getDoctorId();
            System.out.println("medRecId : " + medicalRecord.getMedRecId()) ;
            System.out.println("userId : " + medicalRecord.getUserId()) ;
            System.out.println("doctorId : " + doctorIdStr) ;
            System.out.println("patient : " + medicalRecord.getPatient()) ;
            System.out.println("medContext : " + medicalRecord.getMedContext()) ;
            System.out.println("medDate : " + medicalRecord.getMedDate()) ;
            System.out.println("personal_code : " + medicalRecord.getPersonal_code()) ;
            System.out.println("phone : " + medicalRecord.getPhone()) ;
            System.out.println("hospitalId : " + medicalRecord.getHospitalId()) ;
            System.out.println("-----------------------") ;

            // 진료 기록 추가
            doctorService.addMedRecord(medicalRecord);
        } else {
            System.out.println( "사용자 못 찾음");
            model.addAttribute("error", "잘못된 주민등록번호입니다. 다시 확인해주세요.");
            return "doctor/addMedRecord";
        }
        // 로그인 구현 전까지 임시적 redirect 주소
        return "redirect:/doctor/" + medicalRecord.getDoctorId() + "/medRecordList";
    }
    
 
   

}