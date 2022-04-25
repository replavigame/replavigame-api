package com.replavigame.mobasguide.repository;

import java.util.Optional;

import com.replavigame.mobasguide.entity.MobaHero;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobaHeroRepository extends JpaRepository<MobaHero, Long> {
    Optional<MobaHero> getMobaHeroById(Long id);
}
