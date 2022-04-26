package com.replavigame.mobasguide.repository;

import java.util.Optional;

import com.replavigame.mobasguide.entity.MobaRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobaRoleRepository extends JpaRepository<MobaRole, Long> {
    Optional<MobaRole> getMobaRoleById(Long id);
}
