package com.order_guide.order_guide.controller;

import java.util.List;

import com.order_guide.order_guide.dto.DetailResponse;
import com.order_guide.order_guide.dto.OrderDetailGuideResponse;
import com.order_guide.order_guide.dto.OrderDetailRequest;
import com.order_guide.order_guide.service.OrderDetailGuideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-detail-guides")
public class OrderDetailGuideController {

    @Autowired
    private OrderDetailGuideService orderDetailGuideService;

    @GetMapping
    private ResponseEntity<List<DetailResponse>> getAll() {
        var response = orderDetailGuideService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    private ResponseEntity<OrderDetailGuideResponse> getAllByOrderId(@PathVariable("id") Long id) {
        var response = orderDetailGuideService.getByOrderId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<OrderDetailGuideResponse> create(@RequestBody OrderDetailRequest request) {
        var response = orderDetailGuideService.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Long id) {
        orderDetailGuideService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
