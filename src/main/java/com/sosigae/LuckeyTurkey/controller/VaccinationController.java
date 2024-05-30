package com.sosigae.LuckeyTurkey.controller;

import com.sosigae.LuckeyTurkey.domain.VaccinationRecord;
import com.sosigae.LuckeyTurkey.dto.VaccinationRecordDto;
import com.sosigae.LuckeyTurkey.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    // 사용자 백신 접종 기록
    @GetMapping("/user/{userId}")
    public String getVaccinationForUser(@PathVariable int userId, Model model){
        List<VaccinationRecord> vaccinationRecords = vaccinationService.getVaccinationForUser(userId);
        model.addAttribute("vaccinationRecords", vaccinationRecords);
        model.addAttribute("userId", userId);
        return "vaccine/record";
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
        vaccination.setDoses_received(0);
        vaccination.setRemaining_count(vaccinationDTO.getTotal_doses());
        vaccination.setLatest_date("");
        vaccination.setStart_date(LocalDate.now().toString());

        vaccinationService.addVaccination(vaccination);
        return "redirect:/vaccination/" + vaccinationDTO.getUserId();
    }

    @GetMapping("/{userId}/{vaccineId}/incrementDoses")
    public String incrementDoses(@PathVariable int userId, @PathVariable int vaccineId) {
        vaccinationService.incrementDoses(vaccineId);
        return "redirect:/vaccination/" + userId;
    }

    @GetMapping("/{userId}/{vaccineId}/decrementDoses")
    public String decrementDoses(@PathVariable int userId, @PathVariable int vaccineId) {
        vaccinationService.decrementDoses(vaccineId);
        return "redirect:/vaccination/" + userId;
    }
}
