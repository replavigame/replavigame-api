package com.order_guide.order_guide.model;

import java.util.Date;

import lombok.Data;

@Data
public class User {
    private Long id;

    private Long points;

    private String name;

    private String lastName;

    private Date createdDate;
}
