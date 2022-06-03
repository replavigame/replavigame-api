package com.order_guide.order_guide.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.order_guide.order_guide.model.Coach;
import com.order_guide.order_guide.model.Guide;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_detail_guides")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailGuide {

    @EmbeddedId
    private OrderDetailGuideId orderDetailGuideId = new OrderDetailGuideId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderGuideId")
    @JoinColumn(name = "order_guide_id")
    private OrderGuide orderGuide;

    @Column(name = "price")
    private Long price;

    @Column(name = "coach_id")
    private Long coachId;

    @Transient
    private Coach coach;

    @Transient
    private Guide guide;
}
