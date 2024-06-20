package com.sosigae.LuckeyTurkey.repository;

import com.sosigae.LuckeyTurkey.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
