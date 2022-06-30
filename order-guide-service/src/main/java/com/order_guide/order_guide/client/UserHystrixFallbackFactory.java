package com.order_guide.order_guide.client;

import java.util.Date;

import com.order_guide.order_guide.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserHystrixFallbackFactory implements UserClient {

    @Override
    public ResponseEntity<User> getById(Long id) {
        User user = new User();
        user.setCreatedDate(new Date());
        user.setId(null);
        user.setLastName("undefined");
        user.setName("undefined");
        user.setPoints(0L);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> updateWallet(Long points, Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
