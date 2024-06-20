package com.sosigae.LuckeyTurkey.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DOCTOR")
public class Doctor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
