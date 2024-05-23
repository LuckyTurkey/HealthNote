package com.sosigae.LuckeyTurkey.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {
    private int hospitalId;
    private String id;
    private String password;
    private String name;
    private String address;
    private String openTime;
    private String closeTime;
    private String satOpenTime;
    private String satCloseTime;

    // 진료 과 추가
    private String department;
}
