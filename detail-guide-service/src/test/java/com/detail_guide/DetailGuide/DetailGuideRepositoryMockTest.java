package com.detail_guide.DetailGuide;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.detail_guide.detail_guide.entity.DetailGuide;
import com.detail_guide.detail_guide.repository.DetailGuideRepository;

@DataJpaTest
public class DetailGuideRepositoryMockTest {

    @Autowired
    private DetailGuideRepository detailGuideRepository;

    private DetailGuide detailGuide;

    @BeforeEach
    public void setUp() {
        detailGuide = new DetailGuide();
        detailGuide.setName("dsadsa");
        detailGuide.setVideoUrl("videoUrl");
        detailGuide.setPosition(321321L);
        detailGuide.setGuideId(1L);
     
        detailGuideRepository.save(detailGuide);

    }


    @Test
    @DisplayName("Detail Guide Added")
    public void DetailGuideAdded() {
        DetailGuide detailGuide1 = new DetailGuide();

        detailGuide.setName("dsadsa123");
        detailGuide.setVideoUrl("videoUrl2");
        detailGuide.setPosition(321322L);
        detailGuide.setGuideId(1L);
        detailGuideRepository.save(detailGuide);

        List<DetailGuide> detailGuides = detailGuideRepository.findAll();

        Assertions.assertEquals(2L, detailGuides.size());

    }

    @Test
    @DisplayName("Get A Detail Guide By Id")
    public void GetADetailGuideById() {
        DetailGuide detailGuide = new DetailGuide();

        detailGuide.setName("dsadsa123");
        detailGuide.setVideoUrl("videoUrl2");
        detailGuide.setPosition(321322L);
        detailGuide.setGuideId(1L);
        detailGuideRepository.save(detailGuide);

        Optional<DetailGuide> detailGuides = detailGuideRepository.getDetailGuideById(2L);

        Assertions.assertEquals(detailGuide, detailGuides.get());
    }

    @Test
    @DisplayName("Get A Detail Guide By Id Error")
    public void GetADetailGuideByIdError() {
        

        Optional<DetailGuide> detailGuides = detailGuideRepository.getDetailGuideById(2L);
        Assertions.assertEquals( false, detailGuides.isPresent());

    }

    @Test
    @DisplayName("Get A Detail Guide By Guide Id")
    public void GetADetailGuideByGuideId() {
        DetailGuide detailGuide = new DetailGuide();

        detailGuide.setName("dsadsa123");
        detailGuide.setVideoUrl("videoUrl2");
        detailGuide.setPosition(321322L);
        detailGuide.setGuideId(8L);
        detailGuideRepository.save(detailGuide);

        List<DetailGuide> detailGuides = detailGuideRepository.findAllByGuideId(8L);

        Assertions.assertEquals(1L, detailGuides.size());

    }

    @Test
    @DisplayName("Get A Detail Guide By Guide Id Error")
    public void GetADetailGuideByGuideIdError() {
        DetailGuide detailGuide = new DetailGuide();

        detailGuide.setName("dsadsa123");
        detailGuide.setVideoUrl("videoUrl2");
        detailGuide.setPosition(321322L);
        detailGuide.setGuideId(8L);
        detailGuideRepository.save(detailGuide);

        List<DetailGuide> detailGuides = detailGuideRepository.findAllByGuideId(11L);

        Assertions.assertEquals(0L, detailGuides.size());

    }
    
}
