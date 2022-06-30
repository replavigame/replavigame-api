package com.order_guide.order_guide.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guide {
    private Long id;

    private String title;

    private String description;

    private Long coachId;

    private Category category;

    private Long gameId;

    private Long points;

    private Double descount;
}
