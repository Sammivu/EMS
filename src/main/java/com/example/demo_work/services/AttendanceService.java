package com.example.demo_work.services;

import com.example.demo_work.models.Attendance;

import java.util.List;

public interface AttendanceService {


    List<Attendance> getAttendanceList();

    Attendance saveAttendance(Attendance attendance);

    Attendance getAttendanceById(Long id);

    void deleteAttendanceById(Long id);

}
