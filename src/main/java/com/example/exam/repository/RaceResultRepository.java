package com.example.exam.repository;

import com.example.exam.entity.RaceResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceResultRepository extends JpaRepository<RaceResult, Long> {
 /// OrderByDateAsc
    List<RaceResult> findBySailboatIdOrderByPointsAsc(Long id);

    List<RaceResult> findByRaceIdOrderByPointsAsc(Long id);
}

