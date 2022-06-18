package com.checkout.checkout.client;

import com.checkout.checkout.dto.OrderDetailGameCoinRequest;
import com.checkout.checkout.model.OrderDetailGameCoinPayment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-game-coin-service", path = "/order-detail-game-coins", fallback = OrderGameCoinDetailHystrixFallbackFactory.class)
public interface OrderGameCoinDetailClient {
    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDetailGameCoinPayment> getAllByOrderId(@PathVariable("id") Long id);

    @PostMapping
    public ResponseEntity<OrderDetailGameCoinPayment> create(@RequestBody OrderDetailGameCoinRequest request);

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id);
}
