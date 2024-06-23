package com.example.demo_work.controllers;

import com.example.demo_work.models.Employee;
import com.example.demo_work.models.Leave;
import com.example.demo_work.models.enums.LeaveStatus;
import com.example.demo_work.services.LeaveService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @GetMapping("/leave")
    public String showLeaveForm(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            return "redirect:/login";
        }
        model.addAttribute("leave", new Leave());
        return "leave";
    }

    @PostMapping("/leave")
    public String applyLeave(@ModelAttribute Leave leave, HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            return "redirect:/login";
        }

       // session.getAttribute(employee.getEmployeeId().toString());
        leave.setEmployee(employee);
        leave.setLeaveStatus(LeaveStatus.PENDING);
        leaveService.saveLeave(leave);

        model.addAttribute("leave", employee.getLeaveList());
        model.addAttribute("message", "Submitted Successfully ");
        return "leaveList";
    }

    @GetMapping("/leave/list")
    public String listLeaves(HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            return "/login";
        }
        model.addAttribute("leaves", leaveService.getAllLeaves());
       // return "employeeViewList";
        return "leaveList";
    }

// to update
    @GetMapping("/leave/approve/{id}")
    public String getLeaveApprovalPage(@PathVariable Long id, Model model) {
        Leave leave = leaveService.getLeaveById(id);
        if (leave != null) {
            model.addAttribute("leave", leave);
            return "leaveApproval";
        } else {
            return "redirect:/leave/list";
        }

    }


    @PostMapping("leave/update/{id}")
    public String updateLeave(@PathVariable("id") Long id, @ModelAttribute("leave") Leave leave) {
        Leave existingLeave = leaveService.getLeaveById(id);

        if (existingLeave != null) {
            existingLeave.setLeaveStatus(leave.getLeaveStatus());

            leaveService.updateRecord(existingLeave);
        } else {
            // Handle case where leave with given ID is not found
            throw new RuntimeException("Leave request not found with id: " + id);
        }
        return "redirect:/leave/list";
    }


    @GetMapping("/leave/delete/{id}")
    public String deleteLeave(@PathVariable ("id") Long id){
        leaveService.deleteLeaveById(id);
        return "redirect:/leave/list";
    }


}
