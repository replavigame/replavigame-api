package com.checkout.checkout.model;

import lombok.Data;

@Data
public class User {
    private Long id;

    private Long points;

    private String username;

    private String email;

    private String name;

    private String number;

    private String lastName;
}
