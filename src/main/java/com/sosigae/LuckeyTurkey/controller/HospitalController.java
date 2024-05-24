package com.sosigae.LuckeyTurkey.controller;

import com.sosigae.LuckeyTurkey.domain.Hospital;
import com.sosigae.LuckeyTurkey.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;


    // 병원 세부 정보
    @GetMapping("/hospital/{hospitalId}")
    public String getHospitalDetail(@RequestParam String hospitalId, Model model) {
        Hospital hospital = hospitalService.getHospitalInfo(hospitalId);
        model.addAttribute("hospital", hospital);
        return "hospital/detail";
    }
}
