package com.sosigae.LuckeyTurkey.repository;

import com.sosigae.LuckeyTurkey.domain.MedicalRecord;
import com.sosigae.LuckeyTurkey.dto.MedicalRecordDTO;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer> {
	
	@Query("SELECT mr " +
	           "FROM MedicalRecord mr JOIN Hospital h ON mr.hospitalId = h.hospitalId " +
	           "WHERE mr.userId = :userId")
	    List<MedicalRecord> findMedicalRecordsByUserId(@Param("userId") int userId);
	
	@Query("SELECT mr FROM MedicalRecord mr JOIN FETCH mr.hospital WHERE mr.userId = :userId")
    List<MedicalRecord> findMedicalRecordsWithHospitalInfoByUserId(int userId);
	
} 