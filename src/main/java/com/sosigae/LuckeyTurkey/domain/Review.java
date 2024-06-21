package com.sosigae.LuckeyTurkey.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
    @SequenceGenerator(name = "review_seq", sequenceName = "REVIEW_SEQ", allocationSize = 1)
    private int reviewId;

    @Column(name = "USER_ID", nullable = false)
    private int userId;
    @Column(name = "HOSPITAL_ID", nullable = false)
    private int hospitalId;
    @Column(name = "SCORE")
    private Float score;
    @Column(name = "REVIEW_DATE")
    private LocalDate reviewDate;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "PHOTO")
    private String photo;

    @Transient
    private String userName;
}
