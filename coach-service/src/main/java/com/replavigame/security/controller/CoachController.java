package com.replavigame.security.controller;

import java.util.List;

import com.replavigame.security.dto.AuthenticateRequest;
import com.replavigame.security.dto.CoachRequest;
import com.replavigame.security.dto.CoachResponse;
import com.replavigame.security.service.CoachService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coachs")
public class CoachController {

    @Autowired
    private CoachService userService;

    @GetMapping
    private ResponseEntity<List<CoachResponse>> getAll() {
        var response = userService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<CoachResponse> geById(@PathVariable("id") Long id) {
        var response = userService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    private ResponseEntity<CoachResponse> authenticateUser(@RequestBody AuthenticateRequest request) {
        var response = userService.getByUsernameAndPassword(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<CoachResponse> create(@RequestBody CoachRequest request) {
        var response = userService.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<CoachResponse> create(@RequestBody CoachRequest request, @PathVariable("id") Long id) {
        var response = userService.update(request, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
