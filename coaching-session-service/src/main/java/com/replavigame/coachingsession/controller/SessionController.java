package com.replavigame.coachingsession.controller;

import com.replavigame.coachingsession.dto.SessionRequest;
import com.replavigame.coachingsession.dto.SessionResponse;
import com.replavigame.coachingsession.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping
    private ResponseEntity<List<SessionResponse>> getAll() {
        var response = sessionService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    private ResponseEntity<List<SessionResponse>> getSessionsByRange(@RequestParam("start")Date start, @RequestParam("end")Date end){
        var response = sessionService.getSessionsByRange(start,end);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<SessionResponse> getById(@PathVariable("id") Long id) {
        var response = sessionService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<SessionResponse> create(@RequestBody SessionRequest request) {
        var response = sessionService.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<SessionResponse> update(@RequestBody SessionRequest request, @PathVariable("id") Long id) {
        var response = sessionService.update(request, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Long id) {
        sessionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
