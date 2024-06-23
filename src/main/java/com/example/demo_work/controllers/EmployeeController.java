package com.example.demo_work.controllers;


import com.example.demo_work.models.Employee;
import com.example.demo_work.models.Leave;
import com.example.demo_work.models.enums.Status;
import com.example.demo_work.services.EmployeeService;
import com.example.demo_work.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {


    private final EmployeeService employeeService;
    private final LeaveService leaveService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, LeaveService leaveService) {
        this.employeeService = employeeService;
        this.leaveService = leaveService;
    }


    @GetMapping("/employees")
    public String listEmployees(Model model){
        model.addAttribute("employees", employeeService.getAllEmployees());

        return "employees";
    }

    @GetMapping("/employees/new")
    public String createEmployee(Model model){
        Employee employee= new Employee();
        model.addAttribute("employee", employee);

        return "create_employee";
    }

    @PostMapping("/employees")
    public  String saveEmployees(@ModelAttribute("employees")Employee employee){
        employeeService.saveEmployees(employee);

        return "redirect:/employees";
    }

    //To update records
    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("employee",employeeService.getEmployeeById(id));
        return "edit_employee";
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute Employee employee, Model model){

        Employee existingEmployee = employeeService.getEmployeeById(id);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setDepartment(employee.getDepartment());

        employeeService.updateRecord(existingEmployee);
        model.addAttribute("employee", employeeService.getAllEmployees());
        return "redirect:/employees";
    }
    //To delete records
    @GetMapping("/employees/{id}")
    public  String deleteEmployee (@PathVariable("id") Long id){
        employeeService.deleteStudentById(id);
        return "redirect:/employees";
    }

    @GetMapping("/employees/leaves")
    public String getAllLeaves(Model model){
        List<Leave> leaveApplications = leaveService.getAllLeaves();
        model.addAttribute("leaves", leaveApplications);
        return "leaveList"; //Ensure this matches with Thymeleaf
    }

    @PostMapping("/employees/leaves/updateStatus")
    public String updateLeaveStatus(@RequestParam ("id") Long id, @RequestParam("status") Status leaveStatus, Model model){
        Leave leaveApplication = leaveService.getLeaveById(id);
        leaveApplication.setLeaveStatus(leaveApplication.getLeaveStatus()); // Watch here closely
        leaveService.saveLeave(leaveApplication);

        return "/redirect:/employees";
    }

}
