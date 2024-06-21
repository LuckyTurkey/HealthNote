package com.sosigae.LuckeyTurkey.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MedicalRecordDTO {
	private String hospitalName;
    private Date medDate;
    private String medContext;
	    

	}
