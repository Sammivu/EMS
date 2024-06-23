package com.example.demo_work.controllers;

import com.example.demo_work.models.Attendance;
import com.example.demo_work.models.Employee;
import com.example.demo_work.services.AttendanceService;
import com.example.demo_work.services.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class AttendanceController {


    private AttendanceService attendanceService;
    private  EmployeeService employeeService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService, EmployeeService employeeService) {
        this.attendanceService = attendanceService;
        this.employeeService = employeeService;
    }

    @GetMapping("/attendance")
    public String listAttendance(Model model) {
        model.addAttribute("attendance", attendanceService.getAttendanceList());
        System.out.println("here here");
        System.out.println("attendance list " + attendanceService.getAttendanceList());
        return "attendance";
    }

    @GetMapping("/attendance/mark")
    public String markAttendance(Model model, HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            model.addAttribute("error", "Employee not found");
            return "error";
        }

        Attendance attendance = new Attendance();
        // Initialize status if needed, e.g., attendance.setStatus(Status.AVAILABLE);
        model.addAttribute("attendance", attendance);
        return "mark_attendance";
    }

    @PostMapping("/attendance")
    public String saveAttendance(@ModelAttribute("attendance") Attendance attendance, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            return "error";
        }
       // session.getAttribute(employee.getEmployeeId().toString());
        attendance.setEmployee(employee);
        attendance.setSignIn(LocalDateTime.now());
        attendanceService.saveAttendance(attendance);
        return "dashboard";
    }




}


