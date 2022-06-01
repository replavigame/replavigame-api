package com.user.security.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Email address of the contact.", example = "jessica@ngilang.com", required = false)
    private Date createdDate;

    private Date birthday;

    private Long gameFavorite1;

    private Long gameFavorite2;

    private Long gameFavorite3;
}
