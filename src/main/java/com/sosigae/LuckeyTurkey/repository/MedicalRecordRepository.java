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
	
	@Query("SELECT new com.sosigae.LuckeyTurkey.dto.MedicalRecordDTO(h.name, mr.medDate, mr.medContext, m.medication_taken) " +
	           "FROM MedicalRecord mr " +
	           "JOIN mr.hospital h " +
	           "JOIN mr.medications m " +
	           "WHERE mr.userId = :userId")
	    List<MedicalRecordDTO> findMedicalRecordsByUserId(@Param("userId") int userId);
	
}