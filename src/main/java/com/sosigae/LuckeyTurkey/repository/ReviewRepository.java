package com.sosigae.LuckeyTurkey.repository;

import com.sosigae.LuckeyTurkey.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByHospitalId(String hospitalId);
}
