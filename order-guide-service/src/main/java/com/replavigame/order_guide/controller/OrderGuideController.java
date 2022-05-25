package com.replavigame.order_guide.controller;

import java.util.List;

import com.replavigame.order_guide.dto.OrderGuideRequest;
import com.replavigame.order_guide.dto.OrderGuideResponse;
import com.replavigame.order_guide.service.OrderGuideService;

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
@RequestMapping("/order-guides")
public class OrderGuideController {

    @Autowired
    private OrderGuideService orderGuideService;

    @GetMapping
    private ResponseEntity<List<OrderGuideResponse>> getAll() {
        var response = orderGuideService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    private ResponseEntity<List<OrderGuideResponse>> getAllByCustomerId(@PathVariable("id") Long id) {
        var response = orderGuideService.getAllByCustomerId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/coach/{id}")
    private ResponseEntity<List<OrderGuideResponse>> getAllByCoachId(@PathVariable("id") Long id) {
        var response = orderGuideService.getAllByCoachId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<OrderGuideResponse> getById(@PathVariable("id") Long id) {
        var response = orderGuideService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<OrderGuideResponse> create(@RequestBody OrderGuideRequest request) {
        var response = orderGuideService.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<OrderGuideResponse> update(@RequestBody OrderGuideRequest request,
            @PathVariable("id") Long id) {
        var response = orderGuideService.update(request, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Long id) {
        orderGuideService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
