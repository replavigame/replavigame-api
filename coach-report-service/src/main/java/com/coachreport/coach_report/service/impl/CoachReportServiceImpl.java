package com.coachreport.coach_report.service.impl;

import com.coachreport.coach_report.dto.CoachDocumentResponse;
import com.coachreport.coach_report.dto.CoachReportRequest;
import com.coachreport.coach_report.dto.CoachReportResponse;
import com.coachreport.coach_report.entity.CoachReport;
import com.coachreport.coach_report.repository.CoachReportRepository;
import com.coachreport.coach_report.service.CoachReportService;
import com.coachreport.exception.ResourceNotFoundExceptionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoachReportServiceImpl implements CoachReportService {

    @Autowired
    private CoachReportRepository coachReportRepository;

    private CoachReportResponse convertToResponse(CoachReport entity) {
        CoachReportResponse response = new CoachReportResponse();
        response.setId(entity.getId());
        response.setApproved(entity.getApproved());
        response.setObservation(entity.getObservation());
        response.setQualifiedDate(entity.getQualifiedDate());
        response.setReceivedDate(entity.getQualifiedDate());
        return response;
    }

    private CoachReport convertToEntity(CoachReportRequest request) {
        CoachReport coachReport = new CoachReport();
        coachReport.setApproved(request.getApproved());
        coachReport.setObservation(request.getObservation());
        coachReport.setQualifiedDate(request.getQualifiedDate());
        coachReport.setReceivedDate(request.getReceivedDate());
        return coachReport;
    }


    @Override
    public List<CoachReportResponse> getAll() {
        var entities = coachReportRepository.findAll();
        return entities.stream().map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CoachReportResponse> getAllCoachReportsByRangeOfReceivedDate() {
        return null;
    }

    @Override
    public CoachReportResponse getById(Long id) {
        var entity = coachReportRepository.getCoachReportById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("CoachReport not found"));
        return convertToResponse(entity);
    }

    @Override
    public CoachReportResponse create(CoachReportRequest coachReportRequest) {
        var entity = convertToEntity(coachReportRequest);

        try {
            coachReportRepository.save(entity);
            return convertToResponse(entity);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error occurred while creating CoachReport");
        }
    }

    @Override
    public CoachReportResponse update(CoachReportRequest coachReportRequest, Long id) {
        var entity = coachReportRepository.getCoachReportById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("CoachReport not found"));

        entity = convertToEntity(coachReportRequest);
        entity.setId(id);

        try {
            coachReportRepository.save(entity);
            return convertToResponse(entity);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error occurred while updating CoachReport");
        }
    }

    @Override
    public void delete(Long id) {
        var entity = coachReportRepository.getCoachReportById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("CoachReport not found"));

        try {
            coachReportRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error occurred while deleting CoachReport");
        }
    }
}
