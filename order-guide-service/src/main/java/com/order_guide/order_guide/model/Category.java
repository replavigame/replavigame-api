package com.order_guide.order_guide.model;

import lombok.Data;

@Data
public class Category {
    private Long id;

    private String name;

    private Long gameId;
}
