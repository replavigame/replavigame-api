package com.replavigame.game_coin_offer.repository;

import java.util.Optional;

import com.replavigame.game_coin_offer.entity.GameCoinOffer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameCoinOfferRepository extends JpaRepository<GameCoinOffer, Long> {

    Optional<GameCoinOffer> getGameCoinOfferById(Long id);

}
