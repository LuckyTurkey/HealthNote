package com.sosigae.LuckeyTurkey.dao.mybatis.mapper;

import com.sosigae.LuckeyTurkey.domain.VaccinationRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VaccinationMapper {

    @Select("SELECT * FROM VaccinationRecord WHERE user_id = #{userId}")
    List<VaccinationRecord> getVaccination(int userId);

    @Insert("INSERT INTO VaccinationRecord (user_id, inoculation_name, latest_name, start_date, total_doses, doses_received, remaining_count) " +
            "VALUES (#{userId}, #{inoculation_name}, #{latest_name}, #{start_date}, #{total_doses}, #{doses_received}, #{remaining_count})")
    void addVaccination(VaccinationRecord vaccination);
}
