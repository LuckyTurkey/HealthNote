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

    void addHospitalInfo(Hospital hospital);

    Hospital getHospitalInfo(@Param("hospital_id") String hospital_id);

    List<Hospital> searchHospitalsByNameAndDepartment(@Param("name") String name, @Param("department") String department);

    List<Hospital> searchHospitalsByName(@Param("name") String name);

    List<Hospital> searchHospitalsByDepartment( @Param("department") String department);

    void updateHospitalInfo(Hospital hospital);

    void addDocInfo(Doctor doctor);

    List<Doctor> getDocInfoList(@Param("hospital_id") String hospital_id);

    Doctor getDocInfo(@Param("doctor_id") int doctor_id);

    void updateDocInfo(Doctor doctor);

    void deleteDocInfo(@Param("doctor_id") int doctor_id);

    void addReservation(Reservation reservation);

    List<Reservation> getReservation(@Param("hospital_id") String hospital_id);

    void updateReservation(Reservation reservation);

    void deleteReservation(@Param("hospital_id") String hospital_id, @Param("doctor_id") int doctor_id, @Param("user_id") String user_id);

    List<MedicalRecord> getMedRecordList(@Param("hospital_id") String hospital_id);

    MedicalRecord getMedRecord(@Param("medical_record_id") int medical_record_id);

    List<Hospital> getAllHospitals();
}
