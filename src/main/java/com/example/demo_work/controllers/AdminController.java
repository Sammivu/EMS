package com.example.demo_work.controllers;

import com.example.demo_work.models.Employee;
import com.example.demo_work.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private EmployeeService adminService;

    @GetMapping("/admin/register")
    public String showAdminRegistrationForm(Model model) {
        model.addAttribute("admin", new Employee());
        return "admin_register";
    }

    @PostMapping("/admin/register")
    public String registerAdmin(@ModelAttribute Employee admin, Model model) {
        if (!admin.getEmail().endsWith("@admin.com")) {
            model.addAttribute("error", "Your are not an admin");
            return "admin_register";
        }
        adminService.saveEmployees(admin);
        model.addAttribute("message", "Admin registered successfully!");
        return "/login";
    }


}








