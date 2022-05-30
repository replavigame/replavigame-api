package com.replavigame.coachingsession.service;

import com.replavigame.coachingsession.dto.SessionRequest;
import com.replavigame.coachingsession.dto.SessionResponse;

import java.util.Date;
import java.util.List;

public interface SessionService {
    List<SessionResponse> getAll();
    List<SessionResponse> getSessionsByRange(Date start,Date end);
    SessionResponse getById(Long id);
    SessionResponse create(SessionRequest sessionRequest);
    SessionResponse update(SessionRequest sessionRequest, Long id);
    void delete(Long id);
}
