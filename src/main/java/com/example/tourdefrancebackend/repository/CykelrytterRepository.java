package com.example.tourdefrancebackend.repository;

import com.example.tourdefrancebackend.entity.Cykelrytter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CykelrytterRepository extends JpaRepository<Cykelrytter, Long> {
    List<Cykelrytter> findByCykelholdId(Long holdId);

    Cykelrytter findFirstByOrderBySamletTidAsc();

    Cykelrytter findFirstByOrderByBjergpointDesc();

    Cykelrytter findFirstByOrderBySpurtpointDesc();

    Cykelrytter findFirstBySamletTidOrderByAlderAsc(int i);
}
