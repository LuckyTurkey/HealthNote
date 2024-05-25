package com.sosigae.LuckeyTurkey.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vaccineId;
    private int userId;
    private String inoculation_name;
    private String latest_name;
    //private LocalDate latest_date;
    private String start_date;
    private int total_doses; // 백신 맞아야 하는 총 횟수
    private int doses_received;
    private int remaining_count;

}
