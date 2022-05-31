package com.coachingsession.coaching_session.service;

import com.coachingsession.coaching_session.dto.PlatformRequest;
import com.coachingsession.coaching_session.dto.PlatformResponse;

import java.util.List;

public interface PlatformService {
    List<PlatformResponse> getAll();
    //List<PlatformResponse> getAllBySessionId(Long id);
    PlatformResponse getById(Long id);
    PlatformResponse create(PlatformRequest platformRequest);
    PlatformResponse update(PlatformRequest platformRequest, Long id);
    void delete(Long id);
}
