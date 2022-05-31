package com.coachingsession;

import com.coachingsession.coaching_session.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PlatformRepositoryMockTest {
    @Autowired
    private PlatformRepository platformRepository;



}
