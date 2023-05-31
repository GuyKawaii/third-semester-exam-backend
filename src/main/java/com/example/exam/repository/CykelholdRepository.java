package com.example.exam.repository;

import com.example.exam.entity.Cykelhold;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CykelholdRepository extends JpaRepository<Cykelhold, Long> {
}
