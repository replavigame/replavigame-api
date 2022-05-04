package com.replavigame.mobasguide.gameservice;

import java.util.List;

import com.replavigame.exception.ResourceNotFoundExceptionRequest;
import com.replavigame.mobasguide.entity.MobaRole;
import com.replavigame.mobasguide.repository.MobaRoleRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
final public class MobaRoleRepositoryMockUp {

    @Autowired
    private MobaRoleRepository mobaRoleRepository;

    @Test
    public void whenFindAll_thenReturnListMobaRole() {
        MobaRole mobaRoleTest = MobaRole.builder()
                .id(1L)
                .description("description")
                .name("name").build();
        mobaRoleRepository.save(mobaRoleTest);

        List<MobaRole> mobaRoles = mobaRoleRepository.findAll();
        Assertions.assertEquals(1, mobaRoles.size());
    }

    @Test
    public void whenFindById_thenReturnMobaRole() {
        MobaRole mobaRoleTest = MobaRole.builder()
                .id(1L)
                .description("description")
                .name("name").build();
        mobaRoleRepository.save(mobaRoleTest);

        MobaRole mobaRole = mobaRoleRepository.getMobaRoleById(1L)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Moba Role not found by id"));
        Assertions.assertEquals(1L, mobaRole.getId());
        Assertions.assertEquals("description", mobaRole.getDescription());
        Assertions.assertEquals("name", mobaRole.getName());
    }

}
