package com.order_guide.order_guide.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;

    private Long points;

    private String name;

    private String lastName;

    private Date createdDate;
}
