package com.sosigae.LuckeyTurkey.domain;


import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor implements Serializable {
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
    public String hospitalName;
    public String hospitalAddress;
}
