package com.sosigae.LuckeyTurkey.service;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.VaccinationMapper;
import com.sosigae.LuckeyTurkey.domain.VaccinationRecord;
import com.sosigae.LuckeyTurkey.repository.VaccinationRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccinationService {
    @Autowired
    private VaccinationMapper vaccinationMapper;

    @Autowired
    private VaccinationRecordRepository vaccinationRecordRepository;

    public List<VaccinationRecord> getVaccination(int userId){
        return vaccinationMapper.getVaccination(userId);
    }

    // 사용자 백신 접종 기록
    public List<VaccinationRecord> getVaccinationForUser(int userId) {
        return vaccinationRecordRepository.findByUserId(userId);
    }

    public void addVaccination(VaccinationRecord vaccination){
        vaccinationMapper.addVaccination(vaccination);
    }

    public VaccinationRecord getVaccinationById(int vaccineId) {
        return vaccinationMapper.getVaccinationById(vaccineId);
    }

    @Transactional
    public void incrementDoses(int vaccineId) {
        VaccinationRecord record = vaccinationMapper.getVaccinationById(vaccineId);

        if (record.getDoses_received() <= record.getTotal_doses()) {
            record.setDoses_received(record.getDoses_received() + 1);
            record.setRemaining_count(record.getRemaining_count() - 1);
            record.setLatest_date(LocalDate.now().toString()); // 최근 접종 날짜
            vaccinationMapper.updateVaccinationRecord(record);
        } else {
            throw new IllegalStateException("백신 접종 총 횟수 초과함");
        }
    }

    @Transactional
    public void decrementDoses(int vaccinationRecordId) {
        VaccinationRecord record = vaccinationMapper.getVaccinationById(vaccinationRecordId);

        if (record.getRemaining_count() <= record.getTotal_doses()) {
            int newDosesReceived = record.getDoses_received() - 1;
            int newRemainingCount = record.getRemaining_count() + 1;
            record.setDoses_received(newDosesReceived);
            record.setRemaining_count(newRemainingCount);
            vaccinationMapper.updateVaccinationRecord(record);
        } else {
            throw new RuntimeException("백신 접종 총 횟수 초과함");
        }
    }

}
