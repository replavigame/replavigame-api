package com.checkout.checkout.repository;

import java.util.List;
import java.util.Optional;

import com.checkout.checkout.entity.DetailCard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailCardRepository extends JpaRepository<DetailCard, Long> {
    Optional<DetailCard> getDetailCardById(Long id);

    List<DetailCard> findAllByUserId(Long id);
}
