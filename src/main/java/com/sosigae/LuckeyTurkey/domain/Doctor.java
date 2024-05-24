package com.sosigae.LuckeyTurkey.domain;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
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
}
