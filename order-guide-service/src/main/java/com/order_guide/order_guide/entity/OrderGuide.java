package com.order_guide.order_guide.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.order_guide.order_guide.model.User;

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

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    @Column(name = "sale_created")
    private Date saleCreated;

    @Column(name = "customer_id")
    private Long customerId;

    @Transient
    private User user;
}
