package com.example.demo_work.models;

import com.example.demo_work.models.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "attend_tbl")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attend_id;

    private LocalDateTime signIn;


    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name= "employeeId")
    private Employee employee;


}
