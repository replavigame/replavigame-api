package com.detail_guide.detail_guide.repository;

import java.util.List;
import java.util.Optional;

import com.detail_guide.detail_guide.entity.DetailGuide;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailGuideRepository extends JpaRepository<DetailGuide, Long> {
    Optional<DetailGuide> getDetailGuideById(Long id);

    List<DetailGuide> findAllByGuideId(Long id);
}
