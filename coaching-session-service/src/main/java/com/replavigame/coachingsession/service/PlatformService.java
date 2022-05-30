package com.replavigame.coachingsession.service;

import com.replavigame.coachingsession.dto.PlatformRequest;
import com.replavigame.coachingsession.dto.PlatformResponse;

import java.util.List;

public interface PlatformService {
    List<PlatformResponse> getAll();
    List<PlatformResponse> getAllBySessionId(Long id);
    PlatformResponse getById(Long id);
    PlatformResponse create(PlatformRequest platformRequest);
    PlatformResponse update(PlatformRequest platformRequest, Long id);
    void delete(Long id);
}
