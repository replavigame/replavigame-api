package com.coachreport.coach_report.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tb_coach_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String observation;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    @Column(name = "received_date")
    private Date receivedDate;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    @Column(name = "qualified_date")
    private Date qualifiedDate;
    private Boolean approved;

}
