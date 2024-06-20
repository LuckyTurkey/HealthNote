package com.sosigae.LuckeyTurkey.service;

import com.sosigae.LuckeyTurkey.domain.Reservation;
import com.sosigae.LuckeyTurkey.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public void addReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }
}
