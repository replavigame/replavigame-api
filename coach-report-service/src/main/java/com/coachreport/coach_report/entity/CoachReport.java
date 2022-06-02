package com.coachreport.coach_report.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    @Column(name = "received_at")
    private Date receivedAt;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    @Column(name = "qualified_at")
    private Date qualifiedAt;
    private Boolean approved;


    @PrePersist
    public void prePersist() {
        this.receivedAt = new Date();
    }

}
