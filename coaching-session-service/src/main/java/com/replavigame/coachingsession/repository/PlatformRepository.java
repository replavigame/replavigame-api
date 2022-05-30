package com.replavigame.coachingsession.repository;

import com.replavigame.coachingsession.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlatformRepository extends JpaRepository<Platform,Long> {

    Optional<Platform> getPlatformById(Long id);
    List<Platform> findAllBySessionId(Long sessionId);
}
