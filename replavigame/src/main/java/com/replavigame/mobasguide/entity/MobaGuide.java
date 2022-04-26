package com.replavigame.mobasguide.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "guides")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MobaGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private MobaHero hero;

    @ManyToOne(fetch = FetchType.LAZY)
    private MobaRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    private Double price;
}
