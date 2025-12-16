package com.aviation.aviation_api.repository;

import com.aviation.aviation_api.entity.Gate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GateRepository extends JpaRepository<Gate, Long> {
}
