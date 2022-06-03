package com.coachreport.coach_report.controller;

import com.coachreport.coach_report.dto.CoachDocumentResponse;
import com.coachreport.coach_report.service.CoachDocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/coach-documents")
public class CoachDocumentController {

    @Autowired
    private CoachDocumentService coachDocumentService;

    @GetMapping("/{id}")
    private ResponseEntity<CoachDocumentResponse> getById(@PathVariable("id") Long id) {
        var response = coachDocumentService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<CoachDocumentResponse>> getAllCoachDocuments(){
        List<CoachDocumentResponse> response;
        response = coachDocumentService.getAll();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
