package com.sosigae.LuckeyTurkey.service;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.HospitalMapper;
import com.sosigae.LuckeyTurkey.domain.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private HospitalMapper hospitalMapper;

    // 병원 검색
    public List<Hospital> searchHospitals(String name, String department) {
        if (name != null && !name.isEmpty() && department != null && !department.isEmpty()) {
            return hospitalMapper.searchHospitalsByNameAndDepartment(name, department);
        }
        if (name != null && !name.isEmpty()) {
            return hospitalMapper.searchHospitalsByName(name);
        }
        if (department != null && !department.isEmpty()) {
            return hospitalMapper.searchHospitalsByDepartment(department);
        }
        return new ArrayList<>();
    }

    // 병원 정보
    public Hospital getHospitalInfo(String hospitalId) {
        return hospitalMapper.getHospitalInfo(hospitalId);
    }

}
