package com.order_guide.order_guide.model;

import lombok.Data;

@Data
public class Guide {
    private Long id;

    private String title;

    private String description;

    private Long coachId;

    private Category category;

    private Long gameId;

    private Long points;

    private Double descount;

    private Coach coach;
}
