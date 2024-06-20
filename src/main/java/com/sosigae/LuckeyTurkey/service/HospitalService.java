package com.sosigae.LuckeyTurkey.service;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.DoctorMapper;
import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.HospitalMapper;
import com.sosigae.LuckeyTurkey.domain.Doctor;
import com.sosigae.LuckeyTurkey.domain.Hospital;
import com.sosigae.LuckeyTurkey.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private HospitalMapper hospitalMapper;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private HospitalRepository hospitalRepository;

    // 병원 검색
    public List<Hospital> searchHospitals(String name, String department) {
        if (!name.isEmpty() && !department.isEmpty()) {
            return hospitalMapper.searchHospitalsByNameAndDepartment(name, department);
        }
        if (!name.isEmpty()) {
            return hospitalMapper.searchHospitalsByName(name);
        }
        if (!department.isEmpty()) {
            return hospitalMapper.searchHospitalsByDepartment(department);
        }
        return new ArrayList<>();
    }

    // 병원 정보
    public Hospital getHospitalInfo(String hospitalId) {
        return hospitalMapper.getHospitalInfo(hospitalId);
    }

    // 모든 병원 목록
    public List<Hospital> getAllHospitals() {
        return hospitalMapper.getAllHospitals();
    }

    // 병원에 근무 하는 의사 목록
    public List<Doctor> getDocInfoList(String hospitalId) {
        return hospitalMapper.getDocInfoList(hospitalId);
    }

    public List<Hospital> searchHospitalsByName(String name) {
        return hospitalMapper.searchHospitalsByName(name);
    }

    public List<Doctor> findDoctorsByDoctorName(String doctorName) {
        return doctorMapper.findDoctorsByDoctorName(doctorName);
    }

    public Hospital getHospitalById(String id) {
        return hospitalRepository.findById(id);
    }
}
