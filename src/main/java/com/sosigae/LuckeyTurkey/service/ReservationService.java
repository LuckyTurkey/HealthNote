package com.sosigae.LuckeyTurkey.service;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.ReservationMapper;
import com.sosigae.LuckeyTurkey.domain.Hospital;
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
    @Autowired
    private HospitalService hospitalService;

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

    //유저 아이디로 예약 찾기
//    public List<Reservation> getReservationsByUserId(int userId) {
//        return reservationRepository.findByUserId(userId);
//    }
    public List<Reservation> getReservationsByUserId(int userId) {
        List<Reservation> reservations = reservationRepository.findByUserId(userId);
        for (Reservation reservation : reservations) {
            Hospital hospital = hospitalService.getHospitalId(reservation.getHospitalId());
            reservation.setHospitalName(hospital.getName());
            reservation.setHospitalAddress(hospital.getAddress());
        }
        return reservations;
    }

    // 예약 수정
    public void updateReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    // 예약 삭제
    public void deleteReservation(int reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}
