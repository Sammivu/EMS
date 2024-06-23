package com.example.demo_work.services;

import com.example.demo_work.models.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee saveEmployees(Employee employee);

    Employee getEmployeeById(Long id);

    Employee updateRecord(Employee employee);

    void deleteStudentById(Long id);

    Employee findByEmail(String email);


}
