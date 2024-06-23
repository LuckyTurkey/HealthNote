package com.sosigae.LuckeyTurkey.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;


@Table(name = "VACCINATIONRECORD")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vaccination_record_id;
    private int userId;
    private String inoculation_name;
    private String latest_date;
    private String start_date;
    private int total_doses;
    private int doses_received;
    private int remaining_count;

}
