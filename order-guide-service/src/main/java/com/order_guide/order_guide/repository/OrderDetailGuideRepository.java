package com.order_guide.order_guide.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.order_guide.order_guide.entity.OrderDetailGuide;
import com.order_guide.order_guide.entity.OrderDetailGuideId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailGuideRepository extends JpaRepository<OrderDetailGuide, OrderDetailGuideId> {

    @Query("select o from OrderDetailGuide o where o.orderGuide.id = :orderId")
    @Transactional
    @Modifying
    List<OrderDetailGuide> getAllByOrderGuideId(@Param("orderId") Long orderId);

    @Query("select o from OrderDetailGuide o where o.coachId = :coachId")
    @Transactional
    @Modifying
    List<OrderDetailGuide> getAllByCoachId(@Param("coachId") Long coachId);

    @Query("delete from OrderDetailGuide o where o.orderGuide.id = :orderId")
    @Transactional
    @Modifying
    void deleteAllByOrderGuideId(@Param("orderId") Long orderId);
}
