package com.sosigae.LuckeyTurkey.service;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.DoctorMapper;
import com.sosigae.LuckeyTurkey.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;

    public List<Doctor> searchDoctors(String name, String department) {
        if (!name.isEmpty() && !department.isEmpty()) {
            return doctorMapper.searchDoctorsByNameAndDepartment(name, department);
        }
        if (!name.isEmpty()) {
            return doctorMapper.searchDoctorsByName(name);
        }
        if (!department.isEmpty()) {
            return doctorMapper.searchDoctorsByDepartment(department);
        }
        return new ArrayList<>();
    }

    public Doctor getDocInfo(int doctorId) {
        return doctorMapper.getDocInfo(doctorId);
    }
}
