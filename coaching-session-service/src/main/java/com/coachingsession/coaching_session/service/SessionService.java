package com.coachingsession.coaching_session.service;

import com.coachingsession.coaching_session.dto.SessionRequest;
import com.coachingsession.coaching_session.dto.SessionResponse;

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
