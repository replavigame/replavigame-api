package com.replavigame.mobasguide.controller;

import java.util.List;

import com.replavigame.mobasguide.dto.MobaHeroRequest;
import com.replavigame.mobasguide.dto.MobaHeroResponse;
import com.replavigame.mobasguide.service.MobaHeroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobaheroes")
public class MobaHeroController {

    @Autowired
    private MobaHeroService mobaHeroService;

    @GetMapping
    private ResponseEntity<List<MobaHeroResponse>> getAll() {
        var response = mobaHeroService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<MobaHeroResponse> getById(@PathVariable(name = "id") Long id) {
        var response = mobaHeroService.getMobaHeroById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<MobaHeroResponse> create(@RequestBody MobaHeroRequest request) {
        var response = mobaHeroService.createMobaHero(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<MobaHeroResponse> update(@RequestBody MobaHeroRequest request,
            @PathVariable(name = "id") Long id) {
        var response = mobaHeroService.updateMobaHero(request, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        mobaHeroService.deleteMobaHeroById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
