package com.sosigae.LuckeyTurkey.service;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.ReservationMapper;
import com.sosigae.LuckeyTurkey.domain.Reservation;
import com.sosigae.LuckeyTurkey.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationMapper reservationMapper;

    public void addReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationByHospitalId(int hospitalId) {
        return reservationRepository.findByHospitalId(hospitalId);
    }


    // 병원, 날짜, 시간을 기준으로 예약된 시간 목록을 조회
    public List<String> getReservedTimes(int hospitalId, String date) {
        return reservationMapper.findReservedTimesByHospitalAndDate(hospitalId, date);
    }

    public Reservation getReservationById(int reservationId) {
        return reservationRepository.findById(reservationId).orElse(null);
    }

    public void updateReservation(Reservation reservation) {
        reservationRepository.save(reservation);

    }
}
