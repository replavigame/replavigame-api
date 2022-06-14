package com.order_guide.order_guide.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coach {
    private Long id;

    private String nameCoach;

    private String name;

    private String lastName;

    private Long gameId;
}
