package com.replavigame.order_guide.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_guides")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_price")
    private Long totalPrice;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    @Column(name = "sale_created")
    private Date saleCreated;

    @Column(name = "coach_id")
    private Long coachId;

    @Column(name = "customer_id")
    private Long customerId;
}
