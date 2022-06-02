package com.coachreport.coach_report.controller;

import com.coachreport.coach_report.dto.CoachDocumentRequest;
import com.coachreport.coach_report.dto.CoachDocumentResponse;
import com.coachreport.coach_report.dto.CoachReportRequest;
import com.coachreport.coach_report.dto.CoachReportResponse;
import com.coachreport.coach_report.entity.CoachDocument;
import com.coachreport.coach_report.service.CoachDocumentService;
import com.coachreport.coach_report.service.CoachReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/coach-reports")
public class CoachReportController {

    @Autowired
    private CoachDocumentService coachDocumentService;

    @Autowired
    private CoachReportService coachReportService;

    @GetMapping
    private ResponseEntity<List<CoachReportResponse>> getAllCoachReports(){
        List<CoachReportResponse> response;
        response = coachReportService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<CoachReportResponse> getById(@PathVariable("id") Long id) {
        var response = coachReportService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/coach-documents")
    private ResponseEntity<List<CoachDocumentResponse>> getCoachDocumentsByCoachReportId(@PathVariable("id") Long id) {
        List<CoachDocumentResponse> response;
        response = coachDocumentService.getAllCoachDocumentsByCoachReportId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<CoachReportResponse> create(@RequestBody CoachReportRequest request) {
        var response = coachReportService.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/coach-documents")
    private ResponseEntity<CoachDocumentResponse> create(@PathVariable("id") Long id, @RequestBody CoachDocumentRequest request) {
        var response = coachDocumentService.createCoachDocumentByCoachReportId(request,id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<CoachReportResponse> update(@RequestBody CoachReportRequest request, @PathVariable("id") Long id) {
        var response = coachReportService.update(request, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/coach-documents/{coach-document-id}")
    private ResponseEntity<CoachDocumentResponse> update(@PathVariable("id") Long id,@RequestBody CoachDocumentRequest request, @PathVariable("coach-document-id") Long coachDocId) {
        var response = coachDocumentService.updateCoachDocumentByCoachReportId(request,id,coachDocId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Long id) {
        coachReportService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/coach-documents/{coach-document-id}")
    private ResponseEntity<?> delete(@PathVariable("id") Long id,@PathVariable("coach-document-id") Long coachDocId) {
        coachDocumentService.deleteCoachDocument(id,coachDocId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
