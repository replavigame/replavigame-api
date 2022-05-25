package com.replavigame.security.service;

import java.util.List;

import com.replavigame.security.dto.AuthenticateRequest;
import com.replavigame.security.dto.UserRequest;
import com.replavigame.security.dto.UserResponse;

public interface UserService {
    List<UserResponse> getAll();

    UserResponse getById(Long id);

    UserResponse getByUsernameAndPassword(AuthenticateRequest request);

    UserResponse create(UserRequest request);

    UserResponse update(UserRequest request, Long id);

    void delete(Long id);
}
