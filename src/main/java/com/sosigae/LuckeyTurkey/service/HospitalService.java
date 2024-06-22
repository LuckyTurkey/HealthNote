package com.sosigae.LuckeyTurkey.service;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.DoctorMapper;
import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.HospitalMapper;
import com.sosigae.LuckeyTurkey.domain.Doctor;
import com.sosigae.LuckeyTurkey.domain.Hospital;
import com.sosigae.LuckeyTurkey.domain.User;
import com.sosigae.LuckeyTurkey.repository.DoctorRepository;
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
    @Autowired
    private DoctorRepository doctorRepository;

    // Hospital 객체를 DB에 저장
    public void registerHospital(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    public Hospital findHospitalByLoginId(String loginId){
        return hospitalRepository.findHospitalByLoginId(loginId);
    }

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
    public Hospital getHospitalInfo(int hospitalId) {
        return hospitalMapper.getHospitalInfo(hospitalId);
    }

    // 모든 병원 목록
    public List<Hospital> getAllHospitals() {
        return hospitalMapper.getAllHospitals();
    }

    // 병원에 근무 하는 의사 목록
    public List<Doctor> getDocInfoList(int hospitalId) {
        return hospitalMapper.getDocInfoList(hospitalId);
    }

    public List<Hospital> searchHospitalsByName(String name) {
        return hospitalMapper.searchHospitalsByName(name);
    }

    public List<Doctor> findDoctorsByDoctorName(String doctorName) {
        return doctorMapper.findDoctorsByDoctorName(doctorName);
    }
    public Hospital getHospitalId(int hospitalId){
        return hospitalMapper.getHospitalInfo(hospitalId);
    }
//    public Hospital getHospitalById(String id) {
//        return hospitalRepository.findById(id);
//    }

    public List<Doctor> searchDoctorByNameAndHospitalId(String name, int hospitalId) {
        return doctorRepository.findByNameAndHospitalId(name, hospitalId);
    }

    public List<Doctor> searchDoctorByDepartmentAndHospitalId(String department, int hospitalId) {
        return doctorRepository.findByDepartmentAndHospitalId(department, hospitalId);
    }

	public Hospital loginHospital(String id, String password) {
		Hospital hospital = hospitalRepository.findByIdAndPassword(id, password);
        if (hospital == null) {
            throw new RuntimeException("Invalid credentials");
        }
        return hospital;
	}

	
	    
}
