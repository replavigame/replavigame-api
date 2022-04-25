package com.replavigame.mobasguide.controller;

import java.util.List;

import com.replavigame.mobasguide.dto.MobaGuideRequest;
import com.replavigame.mobasguide.dto.MobaGuideResponse;
import com.replavigame.mobasguide.service.MobaGuideService;

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
@RequestMapping("/mobaguides")
public class MobaGuideRepository {

    @Autowired
    private MobaGuideService mobaGuideService;

    @GetMapping
    private ResponseEntity<List<MobaGuideResponse>> getAll() {
        var response = mobaGuideService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<MobaGuideResponse> getById(@PathVariable(name = "id") Long id) {
        var response = mobaGuideService.getMobaGuideById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<MobaGuideResponse> create(@RequestBody MobaGuideRequest request) {
        var response = mobaGuideService.createMobaGuide(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<MobaGuideResponse> update(@RequestBody MobaGuideRequest request,
            @PathVariable(name = "id") Long id) {
        var response = mobaGuideService.updateMobaGuide(request, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<MobaGuideResponse> delete(@PathVariable(name = "id") Long id) {
        mobaGuideService.deleteMobaGuideById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
