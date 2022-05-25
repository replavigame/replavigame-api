package com.replavigame.order_guide.repository;

import java.util.List;
import java.util.Optional;

import com.replavigame.order_guide.entity.OrderGuide;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGuideRepository extends JpaRepository<OrderGuide, Long> {
    Optional<OrderGuide> getOrderGuideById(Long id);

    List<OrderGuide> findAllByCustomerId(Long id);

    List<OrderGuide> findAllByCoachId(Long id);
}
