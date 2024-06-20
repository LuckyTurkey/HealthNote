package com.sosigae.LuckeyTurkey.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HOSPITAL")
public class Hospital implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "HOSPITAL_ID")
    private int hospitalId;
    private String id;
    private String password;
    @Column(name = "NAME")
    private String name;
    private String address;
    private String openTime;
    private String closeTime;
    private String satOpenTime;
    private String satCloseTime;
    private String email;
    private String phone;

    // 진료 과 추가
    private String department;
    
    /*
    //진료 기록 조회
    @OneToMany(mappedBy = "hospital")
    private List<MedicalRecord> medicalRecords;
    */
}
