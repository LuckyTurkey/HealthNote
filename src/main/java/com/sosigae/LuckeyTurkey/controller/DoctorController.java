package com.sosigae.LuckeyTurkey.controller;

import com.sosigae.LuckeyTurkey.domain.Doctor;
import com.sosigae.LuckeyTurkey.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DoctorController {
    @Autowired
    private DoctorService doctorService;


    @GetMapping("/search/doctors")
    public String searchDoctors(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String department,
                                Model model) {
        List<Doctor> doctors = doctorService.searchDoctors(name, department);
        model.addAttribute("results", doctors);
        return "search/search";
    }
}
