package com.user.security.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserRequest {

    private String username;

    private String password;

    private String email;

    private String name;

    private String number;

    private String lastName;

    @Schema(defaultValue = "hola")
    private Date createdDate;

    private Date birthday;

    private Long gameFavorite1;

    private Long gameFavorite2;

    private Long gameFavorite3;
}
