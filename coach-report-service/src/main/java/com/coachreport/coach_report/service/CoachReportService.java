package com.coachreport.coach_report.service;


import com.coachreport.coach_report.dto.CoachReportRequest;
import com.coachreport.coach_report.dto.CoachReportResponse;


import java.util.List;

public interface CoachReportService {
    List<CoachReportResponse> getAll();
    List<CoachReportResponse> getAllCoachReportsByRangeOfReceivedAt();
    CoachReportResponse getById(Long id);
    CoachReportResponse create(CoachReportRequest coachReportRequest);
    CoachReportResponse update(CoachReportRequest coachReportRequest, Long id);
    void delete(Long id);
}
