package com.order_game_coin.order_game_coin.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class User {
    private Long id;

    private Long points;

    private String name;

    private String lastName;

    @JsonFormat(pattern = "yyyy/MM/dd", shape = Shape.STRING)
    private Date createdDate;
}
