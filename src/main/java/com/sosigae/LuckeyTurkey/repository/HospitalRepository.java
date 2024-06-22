package com.sosigae.LuckeyTurkey.repository;

import com.sosigae.LuckeyTurkey.domain.Hospital;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer>  {

	Hospital findByIdAndPassword(String id, String password);

	@Query("SELECT h FROM Hospital h WHERE h.id = :loginId")
	Hospital findHospitalByLoginId(@Param("loginId") String loginId);

}
