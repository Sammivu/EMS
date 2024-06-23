package com.example.demo_work.controllers;

import com.example.demo_work.models.Employee;
import com.example.demo_work.services.EmployeeService;
import com.example.demo_work.services.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {
        System.out.println(email);
        System.out.println(password);
        Employee employee = loginService.findByEmail(email);
        System.out.println(employee.getPassword());

        if (employee != null && employee.getPassword().equals(password)) {
            // Store employee in session
            session.setAttribute("employee", employee);
            session.setAttribute("employeeId",employee.getEmployeeId());
            model.addAttribute("employee", employee);
            if(email.endsWith("@admin.com")){
                model.addAttribute("employees", employeeService.getAllEmployees());
                return "employees";
            }else{
            return "dashboard";
            }
        }
        // Invalid login
        model.addAttribute("error", true);
        return "login";
    }
    @GetMapping("/dashboard/{id}")
    public String showDashboard(@PathVariable Long id, Model model, HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");

        if (employee != null){
            model.addAttribute("employee", employee);
            System.out.println("Employee in session: " + employee.getFirstName());
        } else {
            System.out.println("No employee in session.");
        }
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
