package com.replavigame.mobasguide.controller;

import java.util.List;

import com.replavigame.mobasguide.dto.GameRequest;
import com.replavigame.mobasguide.dto.GameResponse;
import com.replavigame.mobasguide.service.GameService;

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
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    private ResponseEntity<List<GameResponse>> getAll() {
        var response = gameService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<GameResponse> getById(@PathVariable(name = "id") Long id) {
        var response = gameService.getGameById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<GameResponse> create(@RequestBody GameRequest request) {
        var response = gameService.createGame(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<GameResponse> update(@RequestBody GameRequest request, @PathVariable(name = "id") Long id) {
        var response = gameService.updateGame(request, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> update(@PathVariable(name = "id") Long id) {
        gameService.deleteGameById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
