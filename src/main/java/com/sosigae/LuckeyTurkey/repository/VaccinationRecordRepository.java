package com.sosigae.LuckeyTurkey.repository;

import com.sosigae.LuckeyTurkey.domain.VaccinationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, Long> {
    List<VaccinationRecord> findByUserId(int userId);
}
