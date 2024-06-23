package com.example.demo_work.services.impls;

import com.example.demo_work.models.Leave;
import com.example.demo_work.models.enums.LeaveStatus;
import com.example.demo_work.repositories.LeaveRepository;
import com.example.demo_work.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {


    private final LeaveRepository leaveRepository;

    @Autowired
    public LeaveServiceImpl(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    @Override
    public Leave saveLeave(Leave leave) {
        return leaveRepository.save(leave);
    }


    @Override
    public Leave getLeaveById(Long id) {
        return leaveRepository.findById(id).get();
    }

    @Override
    public Leave updateRecord(Leave leave) {
        return leaveRepository.save(leave);
    }

    public void updateLeaveStatus(Long id, LeaveStatus status) {
        Leave leave = leaveRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid leave ID: " + id));
        leave.setLeaveStatus(status);
        leaveRepository.save(leave);
    }

    @Override
    public void deleteLeaveById(Long id){
        leaveRepository.deleteById(id);
    }

}