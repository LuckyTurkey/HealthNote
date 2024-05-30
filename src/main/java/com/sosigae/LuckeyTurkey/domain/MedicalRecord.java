package com.sosigae.LuckeyTurkey.domain;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord implements Serializable {
    int medRecId;
    String userId;
    int doctorId;
    @NotEmpty(message = "환자 성함을 입력하세요.")
    String patient;
    @NotEmpty(message = "진료 내역을 입력하세요.")
    String medContext;
    @NotNull(message = "진료 날짜를 입력하세요.")
    Date medDate;
    @NotEmpty(message = "환자 주민등록번호를 입력하세요.")
    String personal_code;
    String phone;
}
