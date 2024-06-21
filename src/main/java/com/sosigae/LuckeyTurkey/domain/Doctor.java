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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "DOCTOR_ID")
    private int doctorId;
    private String id;
    private String password;
    private int hospitalId;
    private String name;
    private String department;
    private boolean isAdmin;
    private String formerHospital;
    private String email;
    private String phone;

    // 추가 (검색)
    @Transient
    public String hospitalName;
    @Transient
    public String hospitalAddress;
    
    /*
    @ManyToOne
    @JoinColumn(name = "HOSPITAL_ID", insertable = false, updatable = false) 
    private Hospital hospital;
    */
}
