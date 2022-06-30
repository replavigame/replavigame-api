package com.coachreport.coach_report.service.impl;

import com.coachreport.coach_report.client.CoachClient;
import com.coachreport.coach_report.dto.CoachDocumentResponse;
import com.coachreport.coach_report.dto.CoachReportRequest;
import com.coachreport.coach_report.dto.CoachReportResponse;
import com.coachreport.coach_report.entity.CoachDocument;
import com.coachreport.coach_report.entity.CoachReport;
import com.coachreport.coach_report.models.Coach;
import com.coachreport.coach_report.repository.CoachDocumentRepository;
import com.coachreport.coach_report.repository.CoachReportRepository;
import com.coachreport.coach_report.service.CoachReportService;
import com.coachreport.exception.ResourceNotFoundExceptionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CoachReportServiceImpl implements CoachReportService {

    @Autowired
    private CoachReportRepository coachReportRepository;

    @Autowired
    private CoachDocumentRepository coachDocumentRepository;

    @Qualifier("com.coachreport.coach_report.client.CoachClient")
    @Autowired
    private CoachClient coachClient;

    private CoachReportResponse convertToResponse(CoachReport entity) {
        CoachReportResponse response = new CoachReportResponse();
        response.setId(entity.getId());
        response.setApproved(entity.getApproved());
        response.setObservation(entity.getObservation());
        response.setQualifiedAt(entity.getQualifiedAt());
        response.setReceivedAt(entity.getReceivedAt());
        response.setCoach(entity.getCoach());
        return response;
    }

    private CoachReport convertToEntity(CoachReportRequest request) {
        CoachReport coachReport = new CoachReport();
        coachReport.setApproved(request.getApproved());
        coachReport.setObservation(request.getObservation());
        coachReport.setQualifiedAt(request.getQualifiedAt());
        coachReport.setCoachId(request.getCoachId());
        return coachReport;
    }

    private CoachDocumentResponse convertCoachDocumentToResponse(CoachDocument entity) {
        CoachDocumentResponse response = new CoachDocumentResponse();
        response.setId(entity.getId());
        response.setDocumentUrl(entity.getDocumentUrl());
        response.setTitle(entity.getTitle());
        response.setComment(entity.getComment());
        response.setCoachreportId(entity.getCoachReport().getId());
        return response;
    }


    @Override
    public List<CoachReportResponse> getAll() {
        var entities = coachReportRepository.findAll();
        var responses = entities.stream().map(this::convertToResponse)
                .collect(Collectors.toList());
        responses.forEach(coachReportResponse -> {
            var entitiesCD = coachDocumentRepository.findAllByCoachReportId(coachReportResponse.getId());
            var responsesCD = entitiesCD.stream().map(this::convertCoachDocumentToResponse).collect(Collectors.toList());
            coachReportResponse.setCoachDocuments(responsesCD);
        });

        return responses;
    }

    @Override
    public List<CoachReportResponse> getAllCoachReportsByRangeOfReceivedAt() {
        return null;
    }

    @Override
    public CoachReportResponse getById(Long id) {
        var entity = coachReportRepository.getCoachReportById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("CoachReport not found"));

        Coach coachResponse = coachClient.geById(entity.getCoachId()).getBody();
        entity.setCoach(coachResponse);
        var response = convertToResponse(entity);

        var entitiesCD = coachDocumentRepository.findAllByCoachReportId(response.getId());
        var responsesCD = entitiesCD.stream().map(this::convertCoachDocumentToResponse).collect(Collectors.toList());
        response.setCoachDocuments(responsesCD);


        return response;
    }

    @Override
    public CoachReportResponse create(CoachReportRequest coachReportRequest) {
        var entity = convertToEntity(coachReportRequest);

        try {
            coachReportRepository.save(entity);
            Coach coachResponse = coachClient.geById(entity.getCoachId()).getBody();
            entity.setCoach(coachResponse);
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
        Coach coachResponse = coachClient.geById(entity.getCoachId()).getBody();
        entity.setCoach(coachResponse);

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
            coachReportRepository.deleteById(entity.getId());
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error occurred while deleting CoachReport");
        }
    }
}
