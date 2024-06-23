package com.example.demo_work.services;

import com.example.demo_work.models.Leave;
import com.example.demo_work.models.enums.LeaveStatus;

import java.util.List;

public interface LeaveService {

    List<Leave> getAllLeaves();

    Leave saveLeave(Leave leave);

    Leave getLeaveById(Long id);

    Leave updateRecord(Leave leave);

    void updateLeaveStatus(Long id, LeaveStatus status);

    void deleteLeaveById(Long id);
}
