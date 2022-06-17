package com.coachreport.coach_report.repository;

import com.coachreport.coach_report.entity.CoachReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoachReportRepository extends JpaRepository<CoachReport, Long> {
    Optional<CoachReport> getCoachReportById(Long id);
}
