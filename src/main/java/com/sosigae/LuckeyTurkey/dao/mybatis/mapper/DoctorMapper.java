package com.sosigae.LuckeyTurkey.dao.mybatis.mapper;

import com.sosigae.LuckeyTurkey.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DoctorMapper {

    // 의사 검색
    List<Doctor> searchDoctors(@Param("name") String name, @Param("department") String department);

    List<Doctor> searchDoctorsByNameAndDepartment(@Param("name") String name, @Param("department") String department);

    List<Doctor> searchDoctorsByName(@Param("name") String name);

    List<Doctor> searchDoctorsByDepartment( @Param("department") String department);

    // 특정 환자 백신 접종 현황 확인
    List<VaccinationRecord> getVacRecordByUser(@Param("user_id") String user_id);

    // 특정 환자 약물 복용 기록 확인
    List<Medication> getMedByUser(@Param("user_id") String user_id);

    // 특정 환자 진료 기록 추가
    void addMedRecord(MedicalRecord medicalRecord);

    // 본인이 진료한 모든 환자 진료 기록 확인
    List<MedicalRecord> getMedRecordList(@Param("doctor_id") int doctor_id);

    // 특정 환자 진료 기록 수정
    void updateMedRecord(MedicalRecord medicalRecord);

    // 전체 의사 목록
    List<Doctor> getAllDoctors();

    // 특정 의사 정보 확인
    Doctor getDocInfo(@Param("doctor_id") int doctor_id);

    List<Doctor> findDoctorsByDoctorName(@Param("doctorName") String doctorName);
}
