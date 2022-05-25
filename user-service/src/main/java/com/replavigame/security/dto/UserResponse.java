package com.replavigame.security.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;

    private Long points;

    private String username;

    private String password;

    private String email;

    private String name;

    private String number;

    private String lastName;

    private Date createdDate;

    private Date birthday;

    private Long gameFavorite1;

    private Long gameFavorite2;

    private Long gameFavorite3;
}
