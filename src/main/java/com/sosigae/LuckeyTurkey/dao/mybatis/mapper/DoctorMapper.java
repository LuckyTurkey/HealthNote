package com.sosigae.LuckeyTurkey.dao.mybatis.mapper;

import com.sosigae.LuckeyTurkey.domain.Doctor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DoctorMapper {

    // 의사 검색
    List<Doctor> searchDoctors(@Param("name") String name, @Param("department") String department);
}
