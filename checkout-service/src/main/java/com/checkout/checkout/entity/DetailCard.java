package com.checkout.checkout.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_detailcards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long month;

    private Long day;

    private Long code;

    private Long obfuscateCard;

    private Long userId;

    private String cardholder;
}
