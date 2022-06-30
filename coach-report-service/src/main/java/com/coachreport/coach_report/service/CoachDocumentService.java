package com.coachreport.coach_report.service;

import com.coachreport.coach_report.dto.CoachDocumentRequest;
import com.coachreport.coach_report.dto.CoachDocumentResponse;

import java.util.List;

public interface CoachDocumentService {
    CoachDocumentResponse getById(Long id);
    List<CoachDocumentResponse> getAll();

    List<CoachDocumentResponse> getAllCoachDocumentsByCoachReportId(Long coachReportId);
    CoachDocumentResponse createCoachDocumentByCoachReportId(CoachDocumentRequest coachDocumentRequest, Long coachReportId);
    CoachDocumentResponse updateCoachDocumentByCoachReportId(CoachDocumentRequest coachDocumentRequest, Long coachReportId,Long id);
    void deleteCoachDocument(Long coachReportId,Long id);
}
