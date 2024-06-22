package com.sosigae.LuckeyTurkey.service;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.DoctorMapper;
import com.sosigae.LuckeyTurkey.domain.Doctor;
import com.sosigae.LuckeyTurkey.domain.Hospital;
import com.sosigae.LuckeyTurkey.domain.MedicalRecord;
import com.sosigae.LuckeyTurkey.domain.User;
//import com.sosigae.LuckeyTurkey.repository.DoctorRepository;
import com.sosigae.LuckeyTurkey.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private DoctorRepository doctorRepository;
    
 // Hospital 객체를 DB에 저장
    public void registerDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }
    

    // 의사 검색
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

    // 의사 정보
    public Doctor getDocInfo(int doctorId) {
        return doctorMapper.getDocInfo(doctorId);
    }

    // 전체 의사 목록
    public List<Doctor> getAllDoctors() {
        return doctorMapper.getAllDoctors();
    }

    // 특정 의사 전체 진료 기록 조회
    public List<MedicalRecord> getMedRecordList(int doctorId) {return doctorMapper.getMedRecordList(doctorId);}

    // 의사 환자 진료기록 추가
    public void addMedRecord(MedicalRecord medicalRecord) {doctorMapper.addMedRecord(medicalRecord);}

    // 로그인 id로 doctor 찾기
    public Doctor getDoctorById(String id) {
        return doctorRepository.findById(id);
    }

    // doctorId로 doctor 찾기
    public Doctor findDoctorByDoctorId(int doctorId) {
        return doctorRepository.findByDoctorId(doctorId);
    }

   public Doctor loginDoctor(String id, String password) {
      Doctor doctor = doctorRepository.findByIdAndPassword(id, password);
        if (doctor == null) {
            throw new RuntimeException("Invalid credentials");
        }
        return doctor;
   }



}