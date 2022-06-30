package com.checkout.checkout.controller;

import java.util.List;

import com.checkout.checkout.dto.DetailCardRequest;
import com.checkout.checkout.dto.DetailCardResponse;
import com.checkout.checkout.dto.UserDetailCardResponse;
import com.checkout.checkout.service.DetailCardService;

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
@RequestMapping("/detail-cards")
public class DetailCardController {

    @Autowired
    private DetailCardService cardService;

    @GetMapping
    private ResponseEntity<List<DetailCardResponse>> getAll() {
        var response = cardService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    private ResponseEntity<UserDetailCardResponse> getAllByUserId(@PathVariable("id") Long id) {
        var response = cardService.getAllByUserId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<DetailCardResponse> create(@RequestBody DetailCardRequest request) {
        var response = cardService.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<DetailCardResponse> getById(@RequestBody DetailCardRequest request,
            @PathVariable("id") Long id) {
        var response = cardService.update(request, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> getById(@PathVariable("id") Long id) {
        cardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
