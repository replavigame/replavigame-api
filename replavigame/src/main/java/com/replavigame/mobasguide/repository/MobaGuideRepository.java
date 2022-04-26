package com.replavigame.mobasguide.repository;

import java.util.Optional;

import com.replavigame.mobasguide.entity.MobaGuide;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobaGuideRepository extends JpaRepository<MobaGuide, Long> {
    Optional<MobaGuide> getMobaGuideById(Long id);
}
