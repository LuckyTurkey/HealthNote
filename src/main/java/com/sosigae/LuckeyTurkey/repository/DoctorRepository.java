package com.sosigae.LuckeyTurkey.repository;

import com.sosigae.LuckeyTurkey.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findById(String id);
}
