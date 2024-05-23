package com.sosigae.LuckeyTurkey.dao.mybatis.mapper;

import com.sosigae.LuckeyTurkey.domain.Doctor;
import com.sosigae.LuckeyTurkey.domain.Hospital;
import com.sosigae.LuckeyTurkey.domain.MedicalRecord;
import com.sosigae.LuckeyTurkey.domain.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface HospitalMapper {

    // 병원 정보 추가
    void addHospitalInfo(Hospital hospital);

    // 병원 정보 확인
    Hospital getHospitalInfo(int hospital_id);

    // 병원 검색
//    List<Hospital> searchHospitals(String name);
    List<Hospital> searchHospitals(@Param("name") String name, @Param("department") String department);
    // 병원 정보 수정
    void updateHospitalInfo(Hospital hospital);

    // 해당 병원에서 근무하는 의사 정보 추가
    void addDocInfo(Doctor doctor);

    // 해당 병원에서 근무하는 모든 의사들 정보 확인
    List<Doctor> getDocInfoList(int hostpital_id);

    // 해당 병원에서 근무하는 특정 의사 정보 확인
    Doctor getDocInfo(int doctor_id);

    // 해당 병원에서 근무하는 특정 의사 정보 수정
    void updateDocInfo(Doctor doctor);

    // 해당 병원에서 근무하는 특정 의사 정보 삭제
    void deleteDocInfo(Doctor doctor);

    // 해당 병원에서 예약 스케줄 추가
    void addReservation(Reservation reservation);

    // 해당 병원에서 예약 스케줄 확인
    List<Reservation> getReservation(int hospital_id);

    // 해당 병원에서 예약 스케줄 수정
    void updateReservation(Reservation reservation);

    // 해당 병원에서 예약 스케줄 삭제
    void deleteReservation(Reservation reservation);

    // 해당 병원에서 진행된 모든 진료 기록 리스트로 확인
    List<MedicalRecord> getMedRecordList(int hospital_id);

    // 해당 병원에서 진행된 특정 진료 기록 확인
    MedicalRecord getMedRecord(int medical_record_id);

}
