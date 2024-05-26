package com.sosigae.LuckeyTurkey.controller;

import com.sosigae.LuckeyTurkey.domain.Doctor;
import com.sosigae.LuckeyTurkey.domain.Hospital;
import com.sosigae.LuckeyTurkey.service.DoctorService;
import com.sosigae.LuckeyTurkey.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    // 병원 상세 조회
    @GetMapping("/hospital/{hospitalId}")
    public String getHospitalDetail(@PathVariable String hospitalId, Model model) {
        Hospital hospital = hospitalService.getHospitalInfo(hospitalId);
        List<Doctor> doctors = hospitalService.getDocInfoList(hospitalId);
        model.addAttribute("hospital", hospital);
        model.addAttribute("doctors", doctors);
        return "hospital/detail";
    }
}
