package com.sosigae.LuckeyTurkey.dto;

import lombok.Data;

@Data
public class VaccinationRecordDto {
    private int userId;
    private String inoculation_name;
    private int total_doses;
}
