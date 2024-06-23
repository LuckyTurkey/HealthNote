package com.sosigae.LuckeyTurkey.controller;

import com.sosigae.LuckeyTurkey.domain.Doctor;
import com.sosigae.LuckeyTurkey.domain.Hospital;
import com.sosigae.LuckeyTurkey.service.DoctorService;
import com.sosigae.LuckeyTurkey.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DoctorService doctorService;

    // 로딩 및 검색
    @GetMapping("/search")
    public String searchView(@RequestParam(required = false) String name,
                             @RequestParam(required = false) String department,
                             @RequestParam(defaultValue = "hospitals") String searchType,
                             Model model) {
        // 전체
        if ((name == null || name.isEmpty()) && (department == null || department.isEmpty())) {
            if ("hospitals".equals(searchType)) {
                List<Hospital> hospitals = hospitalService.getAllHospitals();
                model.addAttribute("results", hospitals);
            } else if ("doctors".equals(searchType)) {
                List<Doctor> doctors = doctorService.getAllDoctors();
                model.addAttribute("results", doctors);
            }
        }
        // 필터링
        else {
            if ("hospitals".equals(searchType)) {
                List<Hospital> hospitals = hospitalService.searchHospitals(name, department);
                model.addAttribute("results", hospitals);
            } else if ("doctors".equals(searchType)) {
                List<Doctor> doctors = doctorService.searchDoctors(name, department);
                model.addAttribute("results", doctors);
            }
        }
        model.addAttribute("searchType", searchType);
        model.addAttribute("name", name);
        model.addAttribute("department", department);
        return "search/search";
    }


}
