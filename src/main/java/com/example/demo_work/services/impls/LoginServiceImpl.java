package com.example.demo_work.services.impls;

import com.example.demo_work.models.Employee;
import com.example.demo_work.repositories.EmployeeRepository;
import com.example.demo_work.services.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final EmployeeRepository employeeRepository;

    public LoginServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

}
