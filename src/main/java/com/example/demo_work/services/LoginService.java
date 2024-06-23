package com.example.demo_work.services;

import com.example.demo_work.models.Employee;

public interface LoginService {

    Employee findByEmail (String email);
}
