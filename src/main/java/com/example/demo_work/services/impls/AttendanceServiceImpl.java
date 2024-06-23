package com.example.demo_work.services.impls;

import com.example.demo_work.models.Attendance;
import com.example.demo_work.repositories.AttendanceRepository;
import com.example.demo_work.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private  final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public List<Attendance> getAttendanceList() {
        return attendanceRepository.findAll();
    }

    @Override
    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findById(id).get();
    }

    @Override
    public void deleteAttendanceById(Long id) {
         attendanceRepository.deleteById(id);
    }

}
