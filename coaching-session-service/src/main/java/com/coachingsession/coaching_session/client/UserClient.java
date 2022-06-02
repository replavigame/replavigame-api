package com.coachingsession.coaching_session.client;

import com.coachingsession.coaching_session.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service",fallback = UserHystrixFallbackFactory.class)
public interface UserClient {

    @GetMapping("/users/{id}")
    ResponseEntity<User> geById(@PathVariable("id") Long id);
}
