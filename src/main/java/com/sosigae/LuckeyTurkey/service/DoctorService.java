package com.sosigae.LuckeyTurkey.service;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.DoctorMapper;
import com.sosigae.LuckeyTurkey.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;

    public List<Doctor> searchDoctors(String name, String department) {
        return doctorMapper.searchDoctors(name, department);
    }
}
