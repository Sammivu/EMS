package com.example.demo_work.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_tbl")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long employeeId;

    private String firstName;

    private  String lastName;

    private Double salary;

    private String email;

    private String department;

    private String password;


    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Attendance> attendanceList = new ArrayList<>();


    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Leave> leaveList = new ArrayList<>();



}
