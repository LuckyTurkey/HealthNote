package com.sosigae.LuckeyTurkey.controller;

import com.sosigae.LuckeyTurkey.domain.User;
import com.sosigae.LuckeyTurkey.domain.VaccinationRecord;
import com.sosigae.LuckeyTurkey.dto.VaccinationRecordDto;
import com.sosigae.LuckeyTurkey.service.UserService;
import com.sosigae.LuckeyTurkey.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/vaccination")
public class VaccinationController {
    @Autowired
    private VaccinationService vaccinationService;
    @Autowired
    private UserService userService;

    @GetMapping("/form")
    public String showForm() {
        return "vaccine/vaccineUserForm";
    }

    @GetMapping("/findUserId")
    public String findUserId(@RequestParam("id") String id) {
        User user = userService.findUserById(id);
        int userId = user.getUserId();
        return "redirect:/vaccination/" + userId;
    }

    @GetMapping("/{userId}")
    public String getVaccination(@PathVariable int userId, Model model){
        List<VaccinationRecord> vaccinationRecords = vaccinationService.getVaccination(userId);
        model.addAttribute("vaccinationRecords", vaccinationRecords);
        model.addAttribute("userId", userId);
        return "vaccine/vaccine";
    }

    // 사용자 백신 접종 기록
    @GetMapping("/user/vaccineRecord")
    public String getVaccinationForUser(Model model, HttpSession session) {
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/";
        }
        User user = userService.findUserById(sessionId);
        if (user == null) {
            return "redirect:/";
        }
        List<VaccinationRecord> vaccinationRecords = vaccinationService.getVaccinationForUser(user.getUserId());
        model.addAttribute("vaccinationRecords", vaccinationRecords);
        model.addAttribute("userId", user.getUserId());
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
