package com.coachingsession.coaching_session.repository;

import com.coachingsession.coaching_session.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

    Optional<Session> getSessionById(Long id);


}
