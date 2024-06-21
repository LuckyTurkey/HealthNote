package com.sosigae.LuckeyTurkey.repository;

import com.sosigae.LuckeyTurkey.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findById(String id);

    List<Doctor> findByNameAndHospitalId(String name, int hospitalId);

    List<Doctor> findByDepartmentAndHospitalId(String department, int hospitalId);

    Doctor findByDoctorId(int doctorId);
}
