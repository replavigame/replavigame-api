package com.coachingsession.coaching_session.client;

import com.coachingsession.coaching_session.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserHystrixFallbackFactory implements UserClient {
    @Override
    public ResponseEntity<User> geById(Long id) {
        User user = User.builder()
                .name("unknown")
                .lastName("unknown")
                .number("#0000").build();
        return ResponseEntity.ok(user);
    }
}
