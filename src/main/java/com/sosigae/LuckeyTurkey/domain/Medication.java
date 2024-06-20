package com.sosigae.LuckeyTurkey.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "MEDICATION")
public class Medication implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "MEDICATION_ID", insertable = false, updatable = false)
	private long medication_id;
	@Column(name = "USER_ID")
	private int userId;
	@Column(name = "NAME")
	private String name; 
	private String photo;
	private String dosage_time;
	private int single_dose;
	private int before_meal;
	private String dosage_start_date;
	private String dosage_end_date;
	private String precautions;
	@Column(name = "MEDICATION_TAKEN")
	private int medication_taken;
	
	//진료 기록 조회
	 @ManyToOne
	    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
	    private MedicalRecord medicalRecord;
	
}
