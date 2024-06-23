package com.example.demo_work.models;

import com.example.demo_work.models.enums.LeaveStatus;
import com.example.demo_work.models.enums.Reason;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leave_tbl")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private LocalDate startDate;

   private LocalDate endDate;

   @Enumerated(EnumType.STRING)
   private Reason reason;

   @Enumerated(EnumType.STRING)
   private LeaveStatus leaveStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "employeeId")
    private Employee employee;


}
