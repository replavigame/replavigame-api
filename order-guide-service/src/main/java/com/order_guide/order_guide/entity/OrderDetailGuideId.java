package com.order_guide.order_guide.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class OrderDetailGuideId implements Serializable {
    private Long orderGuideId;

    @Column(name = "guide_id")
    private Long guideId;
}
