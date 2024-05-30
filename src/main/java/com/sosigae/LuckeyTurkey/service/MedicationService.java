package com.sosigae.LuckeyTurkey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.MedicationMapper;
import com.sosigae.LuckeyTurkey.domain.Medication;

@Service
public class MedicationService {
	
	 @Autowired
	 private MedicationMapper medicationMapper;
	 
	 public List<Medication> getMedRecord(long medicationId) {
		 return medicationMapper.getMedRecord(medicationId);	 
	 }

	 public void registerMedRecord(Medication medication) {
		 medicationMapper.registerMedRecord(medication);	        
	 }

	 public Medication getMedDetailInfo(long medicationId) {
		 return medicationMapper.getMedDetailInfo(medicationId);	        
	 }

	    
	    

}
