package com.sosigae.LuckeyTurkey.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEDICALRECORD")
public class MedicalRecord implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEDICAL_RECORD_ID")
	int medRecId;
	@Column(name = "USER_ID")
    int userId;
	@Column(name = "DOCTOR_ID", insertable = false, updatable = false)
    int doctorId;
	@Column(name = "HOSPITAL_ID")
    int hospitalId;
    @NotEmpty(message = "환자 성함을 입력하세요.")
    String patient;
    @NotEmpty(message = "진료 내역을 입력하세요.")
    @Column(name = "MEDCONTEXT")
    String medContext;
    @NotNull(message = "진료 날짜를 입력하세요.")
    @Column(name = "MEDDATE")
    Date medDate;
    @NotEmpty(message = "환자 주민등록번호를 입력하세요.")
    String personal_code;
    String phone;
    
    
    @ManyToOne
    @JoinColumn(name = "HOSPITAL_ID", insertable = false, updatable = false)
    private Hospital hospital;
    
}
