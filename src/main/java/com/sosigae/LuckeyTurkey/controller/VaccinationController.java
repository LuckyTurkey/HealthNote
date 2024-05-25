package com.sosigae.LuckeyTurkey.controller;

import com.sosigae.LuckeyTurkey.domain.VaccinationRecord;
import com.sosigae.LuckeyTurkey.dto.VaccinationRecordDto;
import com.sosigae.LuckeyTurkey.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/vaccination")
public class VaccinationController {
    @Autowired
    private VaccinationService vaccinationService;

    @GetMapping("/{userId}")
    public String getVaccination(@PathVariable int userId, Model model){
        List<VaccinationRecord> vaccinationRecords = vaccinationService.getVaccination(userId);
        model.addAttribute("vaccinationRecords", vaccinationRecords);
        model.addAttribute("userId", userId);
        return "vaccine/vaccine";
    }

    @GetMapping("/create/{userId}")
    public String showVaccinationForm(@PathVariable int userId, Model model) {
        model.addAttribute("userId", userId);
        return "vaccine/vaccineCreate";
    }

    @PostMapping("/create")
    public String addVaccination(@ModelAttribute VaccinationRecordDto vaccinationDTO) {
        VaccinationRecord vaccination = new VaccinationRecord();
        vaccination.setUserId(vaccinationDTO.getUserId());
        vaccination.setInoculation_name(vaccinationDTO.getInoculation_name());
        vaccination.setTotal_doses(vaccinationDTO.getTotal_doses());
        vaccination.setDoses_received(0); // 초기 접종 횟수
        vaccination.setRemaining_count(vaccinationDTO.getTotal_doses()); // 초기 남은 횟수
        vaccination.setLatest_name(""); // 초기 최신 이름 설정
        vaccination.setStart_date(LocalDate.now().toString()); // 시작 날짜 설정

        vaccinationService.addVaccination(vaccination);
        return "redirect:/vaccination/" + vaccinationDTO.getUserId();
    }




}
