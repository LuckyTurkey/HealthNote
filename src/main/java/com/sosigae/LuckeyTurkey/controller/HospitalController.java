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

    // 검색 화면
    @GetMapping("/search")
    public String searchForm() {
        return "search/search";
    }

//    @GetMapping("/search/hospitals")
//    public String searchHospitals(@RequestParam("name") String name, Model model) {
//        List<Hospital> hospitals = hospitalService.searchHospitals(name);
//        model.addAttribute("hospitals", hospitals);
//        return "search/search";
//    }

    @GetMapping("/search/hospitals")
    public String searchHospitals(@RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "department", required = false) String department,
                                  Model model) {
        List<Hospital> hospitals = hospitalService.searchHospitals(name, department);
        model.addAttribute("hospitals", hospitals);
        return "search/search";
    }
}
