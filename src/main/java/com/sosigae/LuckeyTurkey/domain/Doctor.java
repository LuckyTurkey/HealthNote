package com.sosigae.LuckeyTurkey.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity	
@Table(name = "DOCTOR")
public class Doctor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq")
    @SequenceGenerator(name = "doctor_seq", sequenceName = "doctor_seq", allocationSize = 1)
    @Column(name = "DOCTOR_ID")
    private int doctorId;
    private String id;
    private String password;
    
    @Column(name = "HOSPITAL_ID", insertable = false, updatable = false)
    private int hospitalId;
    
    private String name;
    private String department;
    private int is_admin;
    private String formerHospital;
    private String email;
    private String phone;

    // 추가 (검색)
    @Transient
    public String hospitalName;
    @Transient
    public String hospitalAddress;
    
    
    //매핑
    @Transient
    private String hospitalLoginId;

    @ManyToOne
    @JoinColumn(name = "hospital_id", insertable = false, updatable = false)
    private Hospital hospital;

    
}
