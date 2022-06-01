package com.coach.security.service;

import java.util.List;

import com.coach.security.dto.AuthenticateRequest;
import com.coach.security.dto.CoachRequest;
import com.coach.security.dto.CoachResponse;

public interface CoachService {
    List<CoachResponse> getAll();

    CoachResponse getById(Long id);

    CoachResponse getByUsernameAndPassword(AuthenticateRequest request);

    CoachResponse create(CoachRequest request);

    CoachResponse update(CoachRequest request, Long id);

    CoachResponse updateWallet(Long id, Long point);

    void delete(Long id);
}
