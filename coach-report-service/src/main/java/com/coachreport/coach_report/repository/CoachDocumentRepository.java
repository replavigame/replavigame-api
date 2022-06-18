package com.coachreport.coach_report.repository;

import com.coachreport.coach_report.entity.CoachDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CoachDocumentRepository extends JpaRepository<CoachDocument, Long> {
    Optional<CoachDocument> getCoachDocumentById(Long id);

    List<CoachDocument> findAllByCoachReportId(Long coachReportId);
}
