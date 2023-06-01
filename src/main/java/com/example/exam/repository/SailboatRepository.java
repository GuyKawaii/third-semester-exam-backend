package com.example.exam.repository;

import com.example.exam.entity.Sailboat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SailboatRepository extends JpaRepository<Sailboat, Long> {
}
