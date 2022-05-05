package com.replavigame.security.service;

import java.util.List;

import com.replavigame.security.dto.AuthenticateRequest;
import com.replavigame.security.dto.CoachRequest;
import com.replavigame.security.dto.CoachResponse;

public interface CoachService {
    List<CoachResponse> getAll();

    CoachResponse getById(Long id);

    CoachResponse getByUsernameAndPassword(AuthenticateRequest request);

    CoachResponse create(CoachRequest request);

    CoachResponse update(CoachRequest request, Long id);

    void delete(Long id);
}
