package com.order_guide.order_guide.client;

import com.order_guide.order_guide.model.Coach;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CoachHystrixFallbackFactory implements CoachClient {

    @Override
    public ResponseEntity<Coach> geById(Long id) {
        Coach coach = new Coach();
        coach.setId(null);
        coach.setGameId(0L);
        coach.setLastName("undefined");
        coach.setName("undefined");
        coach.setNameCoach("undefined");
        return new ResponseEntity<>(coach, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Coach> updateWallet(Long id, Long point) {
        // TODO Auto-generated method stub
        return null;
    }

}
