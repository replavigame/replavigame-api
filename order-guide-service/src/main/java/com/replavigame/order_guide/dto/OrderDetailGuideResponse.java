package com.replavigame.order_guide.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class OrderDetailGuideResponse {
    private List<DetailResponse> detailResponses;
    private Long customerId;
    private Long coachId;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm", shape = Shape.STRING)
    private Date saleCreated;
    private Long total;
    private Long quantifyProducts;
}
