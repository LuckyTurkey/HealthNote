package com.sosigae.LuckeyTurkey.dao.mybatis.mapper;

import com.sosigae.LuckeyTurkey.domain.Reservation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReservationMapper {
    @Insert("INSERT INTO reservation (doctor_id, hospital_id, user_id, reservation_date, reservation_time, created_at) " +
            "VALUES (#{doctorId}, #{hospitalId}, #{userId}, #{reservationDate}, #{reservationTime}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "reservationId")
    void addReservation(Reservation reservation);

    @Select("SELECT reservation_time FROM reservation WHERE hospital_id = #{hospitalId} AND reservation_date = #{date}")
    List<String> findReservedTimesByHospitalAndDate(@Param("hospitalId") int hospitalId, @Param("date") String date);
}
