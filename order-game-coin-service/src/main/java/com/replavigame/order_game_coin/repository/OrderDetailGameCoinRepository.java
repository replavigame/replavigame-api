package com.replavigame.order_game_coin.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.replavigame.order_game_coin.entity.OrderDetailGameCoin;
import com.replavigame.order_game_coin.entity.OrderDetailGameCoinId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailGameCoinRepository extends JpaRepository<OrderDetailGameCoin, OrderDetailGameCoinId> {

    @Query("delete from OrderDetailGameCoin o where o.orderGameCoin.id = :orderId")
    @Transactional
    @Modifying
    void deleteAllByOrderId(@Param("orderId") Long orderId);

    @Query("select o from OrderDetailGameCoin o where o.orderGameCoin.id = :orderId")
    @Transactional
    @Modifying
    List<OrderDetailGameCoin> getAllByOrderId(@Param("orderId") Long orderId);

}
