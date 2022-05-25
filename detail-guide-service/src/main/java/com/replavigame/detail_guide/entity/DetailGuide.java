package com.replavigame.detail_guide.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_detail_guides")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailGuide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    @Column(name = "video_url")
    private String videoUrl;

    private Long position;

    @Column(name = "guide_id")
    private Long guideId;
}
