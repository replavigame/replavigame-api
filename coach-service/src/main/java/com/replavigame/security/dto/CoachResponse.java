package com.replavigame.security.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CoachResponse {
    private Long id;

    private Long points;

    private String username;

    private String nameCoach;

    private String description;

    private String password;

    private String email;

    private String name;

    private String number;

    private String lastName;

    private Date createdDate;

    private Date birthday;

    private Long gameId;
}
