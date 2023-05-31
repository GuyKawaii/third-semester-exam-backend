package com.example.exam.repository;

import com.example.exam.entity.Cykelrytter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CykelrytterRepository extends JpaRepository<Cykelrytter, Long> {
    List<Cykelrytter> findByCykelholdId(Long holdId);

    Cykelrytter findFirstByOrderBySamletTidAsc();

    Cykelrytter findFirstByOrderByBjergpointDesc();

    Cykelrytter findFirstByOrderBySpurtpointDesc();

    // syntax - https://www.baeldung.com/spring-data-jpa-query
    @Query("SELECT c FROM Cykelrytter c WHERE c.alder < :age ORDER BY c.samletTid ASC LIMIT 1")
    Cykelrytter findFirstBySamletTidOrderByAlderAscLessThan(@Param("age") int age);


}
