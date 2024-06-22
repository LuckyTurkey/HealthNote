package com.sosigae.LuckeyTurkey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sosigae.LuckeyTurkey.domain.Medication;
import com.sosigae.LuckeyTurkey.service.MedicationService;

@RestController
@RequestMapping("/medication")
public class MedicationController {
	
	@Autowired
    private MedicationService medicationService;

    // 복용 약물 기록 조회
    @GetMapping("/{medication_id}")
    public List<Medication> getMedRecord(@PathVariable("medication_id") long medicationId) {
        return medicationService.getMedRecord(medicationId);
    }

    // 복용 약물 등록
    @PostMapping("/register")
    public void registerMedRecord(@RequestBody Medication medication) {
        medicationService.registerMedRecord(medication);
    }

    // 약물 세부 정보 조회
    @GetMapping("/detail/{medication_id}")
    public Medication getMedDetailInfo(@PathVariable("medication_id") long medicationId) {
        return medicationService.getMedDetailInfo(medicationId);
    }
    

}
