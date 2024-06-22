package com.sosigae.LuckeyTurkey.repository;

import com.sosigae.LuckeyTurkey.domain.Reservation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByHospitalId(int hospitalId);

    @Query("SELECT r FROM Reservation r WHERE r.reservationId = :reservationId")
    Reservation findReservationById(int reservationId);

    List<Reservation> findByUserId(int userId);
}
