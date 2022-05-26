package com.replavigame.order_game_coin.controller;

import java.util.List;

import com.replavigame.order_game_coin.dto.OrderDetailGameCoinRequest;
import com.replavigame.order_game_coin.dto.OrderDetailGameCoinResponse;
import com.replavigame.order_game_coin.dto.OrderDetailSimpleResponse;
import com.replavigame.order_game_coin.service.OrderDetailGameCoinService;

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
@RequestMapping("/order-detail-game-coins")
public class OrderDetailGameCoinController {

    @Autowired
    private OrderDetailGameCoinService orderDetailGameCoinService;

    @GetMapping
    private ResponseEntity<List<OrderDetailSimpleResponse>> getAll() {
        var response = orderDetailGameCoinService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    private ResponseEntity<OrderDetailGameCoinResponse> getAllByOrderId(@PathVariable("id") Long id) {
        var response = orderDetailGameCoinService.getAllByOrderId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<OrderDetailGameCoinResponse> create(@RequestBody OrderDetailGameCoinRequest request) {
        var response = orderDetailGameCoinService.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Long id) {
        orderDetailGameCoinService.deleteByOrderId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
