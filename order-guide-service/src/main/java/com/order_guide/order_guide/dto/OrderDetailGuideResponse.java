package com.order_guide.order_guide.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.order_guide.order_guide.model.User;

import lombok.Data;

@Data
public class OrderDetailGuideResponse {
    private List<DetailResponse> detailResponses;
    private Long customerId;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm", shape = Shape.STRING)
    private Date saleCreated;
    private Long quantifyProducts;
    private User user;
}
