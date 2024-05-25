package com.sosigae.LuckeyTurkey.service;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.VaccinationMapper;
import com.sosigae.LuckeyTurkey.domain.VaccinationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationService {
    @Autowired
    private VaccinationMapper vaccinationMapper;

    public List<VaccinationRecord> getVaccination(int userId){
        return vaccinationMapper.getVaccination(userId);
    }

    public void addVaccination(VaccinationRecord vaccination){
        vaccinationMapper.addVaccination(vaccination);
    }
}
