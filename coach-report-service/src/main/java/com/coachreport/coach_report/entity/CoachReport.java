package com.coachreport.coach_report.entity;

import com.coachreport.coach_report.models.Coach;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Table(name="tb_coach_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coach_id")
    private Long coachId;

    @Transient
    private Coach coach;

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
