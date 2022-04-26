package com.replavigame.mobasguide.repository;

import java.util.Optional;

import com.replavigame.mobasguide.entity.Game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> getGameById(Long id);
}
