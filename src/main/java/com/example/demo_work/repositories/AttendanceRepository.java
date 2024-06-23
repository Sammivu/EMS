package com.example.demo_work.repositories;

import com.example.demo_work.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
//    Attendance findByEmployeeIdAndSignOutTimeIsNull(Long employeeId);


}
