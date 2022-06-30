package com.capture.capture.controller;

import java.util.List;

import com.capture.capture.entity.Capture;
import com.capture.capture.service.CaptureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/captures")
public class CaptureController {

    @Autowired
    private CaptureService captureService;

    @GetMapping
    private ResponseEntity<List<Capture>> getAll() {
        var response = captureService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cancel")
    private ResponseEntity<Capture> cancel(@RequestParam(value = "token") String token) {

        var response = captureService.cancel(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/accept")
    private ResponseEntity<Capture> accept(
            @RequestParam(value = "paymentId") String paymentId, @RequestParam(value = "token") String token,
            @RequestParam(value = "PayerID") String PayerID) {

        var response = captureService.accept(paymentId, token, PayerID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
