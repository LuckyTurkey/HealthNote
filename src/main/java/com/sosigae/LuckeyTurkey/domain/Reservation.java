package com.sosigae.LuckeyTurkey.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int reservationId;

    @Column(name = "doctor_id", nullable = false)
    private int doctorId;

    @Column(name = "hospital_id", nullable = false)
    private int hospitalId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "reservation_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reservationDate;

    @Column(name = "reservation_time", nullable = false)
    private String reservationTime;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // 병원 예약 생성
    @Transient
    private String name;

    @Transient
    private String phone;

    @Transient
    private String hospitalName;

    @Transient
    private String hospitalAddress;
}
