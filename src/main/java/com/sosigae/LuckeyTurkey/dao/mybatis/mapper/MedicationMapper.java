package com.sosigae.LuckeyTurkey.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sosigae.LuckeyTurkey.domain.Medication;

public interface MedicationMapper {
	
	//복용 약물 확인
	List<Medication> getMedRecord(@Param("medication_id")long medication_id);
	
	//복용 약물 등록
	void registerMedRecord(Medication medication);
	
	//약물 세부 정보 확인
	Medication getMedDetailInfo(@Param("medication_id")long medication_id);
	
	
}
