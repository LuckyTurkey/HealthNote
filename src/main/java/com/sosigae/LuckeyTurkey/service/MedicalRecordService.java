package com.sosigae.LuckeyTurkey.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sosigae.LuckeyTurkey.domain.MedicalRecord;
import com.sosigae.LuckeyTurkey.dto.MedicalRecordDTO;
import com.sosigae.LuckeyTurkey.repository.MedicalRecordRepository;

@Service
@Transactional
public class MedicalRecordService {

	@Autowired
    private MedicalRecordRepository medicalRecordRepository;

	public List<MedicalRecordDTO> getMedicalRecordsByUserId(int userId) {
        return medicalRecordRepository.findMedicalRecordsByUserId(userId);
    }
}
