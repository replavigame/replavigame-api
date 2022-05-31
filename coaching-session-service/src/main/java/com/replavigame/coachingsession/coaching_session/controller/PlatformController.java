package com.replavigame.coachingsession.coaching_session.controller;

import com.replavigame.coachingsession.coaching_session.dto.PlatformRequest;
import com.replavigame.coachingsession.coaching_session.dto.PlatformResponse;
import com.replavigame.coachingsession.coaching_session.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/platforms")
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    @GetMapping
    private ResponseEntity<List<PlatformResponse>> getAll() {
        var response = platformService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<PlatformResponse> getById(@PathVariable("id") Long id) {
        var response = platformService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<PlatformResponse> create(@RequestBody PlatformRequest request) {
        var response = platformService.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<PlatformResponse> update(@RequestBody PlatformRequest request, @PathVariable("id") Long id) {
        var response = platformService.update(request, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Long id) {
        platformService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
