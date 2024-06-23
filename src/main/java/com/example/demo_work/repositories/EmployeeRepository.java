package com.example.demo_work.repositories;

import com.example.demo_work.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //Checks if email exist
    Employee findByEmail(String email);
}
