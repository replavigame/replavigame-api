package com.replavigame.coachingsession;

import com.replavigame.coachingsession.entity.Platform;
import com.replavigame.coachingsession.repository.PlatformRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PlatformRepositoryMockTest {
    @Autowired
    private PlatformRepository platformRepository;



}
