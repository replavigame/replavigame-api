package com.replavigame.order_game_coin.controller;

import java.util.List;

import com.replavigame.order_game_coin.dto.OrderGameCoinRequest;
import com.replavigame.order_game_coin.dto.OrderGameCoinResponse;
import com.replavigame.order_game_coin.service.OrderGameCoinService;

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
@RequestMapping("/order-game-coins")
public class OrderGameCoinController {

    @Autowired
    private OrderGameCoinService orderGameCoinService;

    @GetMapping
    private ResponseEntity<List<OrderGameCoinResponse>> getAll() {
        var response = orderGameCoinService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<OrderGameCoinResponse> getById(@PathVariable("id") Long id) {
        var response = orderGameCoinService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<OrderGameCoinResponse> getById(@RequestBody OrderGameCoinRequest request) {
        var response = orderGameCoinService.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<OrderGameCoinResponse> update(@RequestBody OrderGameCoinRequest request,
            @PathVariable("id") Long id) {
        var response = orderGameCoinService.update(request, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Long id) {
        orderGameCoinService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
