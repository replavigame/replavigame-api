package com.replavigame.security.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CoachRequest {

    private String username;

    private String password;

    private String email;

    private String description;

    private String name;

    private String number;

    private String lastName;

    private String nameCoach;

    private Date createdDate;

    private Date birthday;

    private Long gameId;
}
