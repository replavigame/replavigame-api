package com.coachreport.coach_report.service.impl;

import com.coachreport.coach_report.dto.CoachDocumentRequest;
import com.coachreport.coach_report.dto.CoachDocumentResponse;
import com.coachreport.coach_report.entity.CoachDocument;
import com.coachreport.coach_report.repository.CoachDocumentRepository;
import com.coachreport.coach_report.repository.CoachReportRepository;
import com.coachreport.coach_report.service.CoachDocumentService;
import com.coachreport.exception.ResourceNotFoundExceptionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CoachDocumentServiceImpl implements CoachDocumentService {

    @Autowired
    private CoachDocumentRepository coachDocumentRepository;

    @Autowired
    private CoachReportRepository coachReportRepository;

    private CoachDocumentResponse convertToResponse(CoachDocument entity) {
        CoachDocumentResponse response = new CoachDocumentResponse();
        response.setId(entity.getId());
        response.setDocumentUrl(entity.getDocumentUrl());
        response.setTitle(entity.getTitle());
        response.setComment(entity.getComment());
        response.setCoachreportId(entity.getCoachReport().getId());
        return response;
    }

    private CoachDocument convertToEntity(CoachDocumentRequest request) {
        CoachDocument coachDocument = new CoachDocument();
        coachDocument.setDocumentUrl(request.getDocumentUrl());
        coachDocument.setTitle(request.getTitle());
        coachDocument.setComment(request.getComment());
        return coachDocument;
    }


    @Override
    public CoachDocumentResponse getById(Long id) {
        var entity = coachDocumentRepository.getCoachDocumentById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("CoachDocument not found"));
        return convertToResponse(entity);
    }

    @Override
    public List<CoachDocumentResponse> getAll() {
        var entities = coachDocumentRepository.findAll();
        return entities.stream().map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CoachDocumentResponse> getAllCoachDocumentsByCoachReportId(Long coachReportId) {
        var entities = coachDocumentRepository.findAllByCoachReportId(coachReportId);
        return entities.stream().map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CoachDocumentResponse createCoachDocumentByCoachReportId(CoachDocumentRequest coachDocumentRequest, Long coachReportId) {
        var coachReport = coachReportRepository.getCoachReportById(coachReportId)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("CoachReport not found"));


        var entity = convertToEntity(coachDocumentRequest);
        entity.setCoachReport(coachReport);

        try {
            coachDocumentRepository.save(entity);
            return convertToResponse(entity);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error occurred while creating CoachDocument");
        }

    }

    @Override
    public CoachDocumentResponse updateCoachDocumentByCoachReportId(CoachDocumentRequest coachDocumentRequest, Long coachReportId, Long id) {
        var coachReport = coachReportRepository.getCoachReportById(coachReportId)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("CoachReport not found"));
        var coachDocument = coachDocumentRepository.getCoachDocumentById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("CoachDocument not found"));

        coachDocument = convertToEntity(coachDocumentRequest);
        coachDocument.setId(id);
        coachDocument.setCoachReport(coachReport);

        try {
            coachDocumentRepository.save(coachDocument);
            return convertToResponse(coachDocument);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error occurred while updating CoachDocument");
        }
    }

    @Override
    public void deleteCoachDocument(Long coachReportId, Long id) {
        var coachReport = coachReportRepository.getCoachReportById(coachReportId)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("CoachReport not found"));
        var coachDocument = coachDocumentRepository.getCoachDocumentById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("CoachDocument not found"));

        try {
            coachDocumentRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error occurred while deleting CoachDocument");
        }
    }
}
