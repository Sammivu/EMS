package com.example.demo_work.repositories;

import com.example.demo_work.models.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
}
