package com.coachingsession.coaching_session.entity;

import com.coachingsession.coaching_session.models.Coach;
import com.coachingsession.coaching_session.models.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tb_sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    @Column(name = "start_date")
    private Date startDate;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    @Column(name = "end_date")
    private Date endDate;
    private Boolean available;

    @Column(name = "coach_id")
    private Long coachId;

    @Transient
    private Coach coach;

    @Column(name = "user_id")
    private Long userId;

    @Transient
    private User user;


}
