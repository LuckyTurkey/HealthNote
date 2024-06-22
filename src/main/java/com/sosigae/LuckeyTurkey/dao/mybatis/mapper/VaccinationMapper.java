package com.sosigae.LuckeyTurkey.dao.mybatis.mapper;

import com.sosigae.LuckeyTurkey.domain.VaccinationRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VaccinationMapper {

    @Select("SELECT * FROM VaccinationRecord WHERE user_id = #{userId}")
    List<VaccinationRecord> getVaccination(int userId);

    @Insert("INSERT INTO VaccinationRecord (user_id, inoculation_name, latest_date, start_date, total_doses, doses_received, remaining_count) " +
            "VALUES (#{userId}, #{inoculation_name}, #{latest_date}, #{start_date}, #{total_doses}, #{doses_received}, #{remaining_count})")
    void addVaccination(VaccinationRecord vaccination);

    @Select("SELECT * FROM VaccinationRecord WHERE vaccination_record_id = #{vaccination_record_id}")
    VaccinationRecord getVaccinationById(int vaccineId);

    @Update("UPDATE VaccinationRecord " +
            "SET doses_received = #{doses_received}, remaining_count = #{remaining_count}, latest_date=#{latest_date} " +
            "WHERE vaccination_record_id = #{vaccination_record_id}")
    void updateVaccinationRecord(VaccinationRecord vaccination);
}
