package com.replavigame.coachingsession;

import com.replavigame.coachingsession.dto.PlatformResponse;
import com.replavigame.coachingsession.entity.Platform;
import com.replavigame.coachingsession.repository.PlatformRepository;
import com.replavigame.coachingsession.service.PlatformService;
import com.replavigame.coachingsession.service.impl.PlatformServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class PlatformServiceMockTest {
    @MockBean
    private PlatformRepository platformRepository;
    @Autowired
    private PlatformService platformService;

    @TestConfiguration
    static class PlatformServiceTestConfiguration{
        @Bean
        public PlatformService platformService(){
            return new PlatformServiceImpl();
        }

    }

    @Test
    public void WhenGetAPlatformWithExistingId(){
        long platformId = 1;
        PlatformResponse platform = new PlatformResponse();
        Platform platform1 = new Platform();
        platform.setId(platformId);
        platform1.setId(platformId);
        when(platformRepository.findById(platformId)).thenReturn(Optional.of(platform1));

        PlatformResponse result = platformService.getById(platformId);
        assertThat(result).isEqualTo(platform);
    }

}
