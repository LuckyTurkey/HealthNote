package com.sosigae.LuckeyTurkey.service;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.HospitalMapper;
import com.sosigae.LuckeyTurkey.domain.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private HospitalMapper hospitalMapper;

    // 병원 검색
//    public List<Hospital> searchHospitals(String name) {
//        return hospitalMapper.searchHospitals(name);
//    }

    public List<Hospital> searchHospitals(String name, String department) {
        return hospitalMapper.searchHospitals(name, department);
    }


}
