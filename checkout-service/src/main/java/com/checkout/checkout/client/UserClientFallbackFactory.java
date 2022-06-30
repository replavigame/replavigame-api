package com.checkout.checkout.client;

import com.checkout.checkout.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallbackFactory implements UserClient {

    @Override
    public ResponseEntity<User> geById(Long id) {
        User user = new User();
        user.setId(null);
        user.setEmail("undefined");
        user.setLastName("undefined");
        user.setName("undefined");
        user.setNumber("undefined");
        user.setPoints(0L);
        user.setUsername("undefined");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> updateWallet(Long points, Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
