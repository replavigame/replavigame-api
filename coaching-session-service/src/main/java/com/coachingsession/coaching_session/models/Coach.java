package com.coachingsession.coaching_session.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coach {
    private Long id;
    private String nameCoach;
    private String name;
    private String lastName;
    private Long gameId;
}
