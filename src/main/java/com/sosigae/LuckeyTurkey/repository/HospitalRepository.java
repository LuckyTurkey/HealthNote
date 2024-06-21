package com.sosigae.LuckeyTurkey.repository;

import com.sosigae.LuckeyTurkey.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer>  {

	Hospital findByIdAndPassword(String id, String password);
    //Hospital findById(String id);
    
    //Hospital findByUserId(int userId);
}
