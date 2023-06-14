package com.ecodrive.platform.u202110085.repository;

import com.ecodrive.platform.u202110085.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    boolean existsByDriverId(Long driverId);

    @Query("SELECT MAX(s.value) FROM Score s WHERE s.driverId = :driverId")
    Float findMaxScoreByDriverId(Long driverId);

    @Query("SELECT AVG(s.value) FROM Score s WHERE s.driverId = :driverId")
    Float findAverageScoreByDriverId(Long driverId);
}
