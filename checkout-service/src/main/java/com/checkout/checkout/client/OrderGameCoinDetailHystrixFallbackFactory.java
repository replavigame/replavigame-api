package com.checkout.checkout.client;

import com.checkout.checkout.dto.OrderDetailGameCoinRequest;
import com.checkout.checkout.model.OrderDetailGameCoinPayment;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderGameCoinDetailHystrixFallbackFactory implements OrderGameCoinDetailClient {

    @Override
    public ResponseEntity<OrderDetailGameCoinPayment> getAllByOrderId(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<OrderDetailGameCoinPayment> create(OrderDetailGameCoinRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        // TODO Auto-generated method stub
        return null;
    }
}
