package com.joelmaciel.food.domain.service.impl.integration;

import com.joelmaciel.food.api.dto.request.KitchenRequestDTO;
import com.joelmaciel.food.api.dto.response.KitchenDTO;
import com.joelmaciel.food.domain.exception.EntityInUseException;
import com.joelmaciel.food.domain.exception.KitchenNotFoundException;
import com.joelmaciel.food.domain.repository.KitchenRepository;
import com.joelmaciel.food.domain.service.KitchenService;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestPropertySource("/application-test.yml")
class KitchenServiceIT {

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    void setUp() {
        flyway.migrate();
    }

    @Test
    @DisplayName("Should list all kitchens successfully")
    void shouldListAllKitchensSuccessfully() {
        List<KitchenDTO> kitchenDTOList = kitchenService.findAll();

        assertThat(kitchenDTOList).isNotEmpty();

        assertThat(kitchenDTOList.size()).isEqualTo(5);

    }

    @Test
    @DisplayName("Should save kitchen successfully when valid data is provided")
    void shouldSaveSuccessfulKitchen() {
        KitchenRequestDTO kitchenRequestDTO = new KitchenRequestDTO();
        kitchenRequestDTO.setName("French");

        KitchenDTO kitchenDTO = kitchenService.save(kitchenRequestDTO);

        assertThat(kitchenDTO).isNotNull();
        assertThat(kitchenDTO.getId()).isNotNull();
        assertThat(kitchenDTO.getName()).isEqualTo("French");
    }

    @Test
    @DisplayName("Should throw an exception When registering Kitchen without a Name")
    void shouldThrowAnException_WhenRegisteringKitchenWithoutAName() {
        KitchenRequestDTO kitchenRequestDTO = new KitchenRequestDTO();
        kitchenRequestDTO.setName(null);

        DataIntegrityViolationException exception = assertThrows(DataIntegrityViolationException.class, () -> {
            kitchenService.save(kitchenRequestDTO);
        });
        assertThat(exception).isNotNull();
    }

    @Test
    @DisplayName("Should return Successfully Kitchen When searching for Valid Id")
    void shouldReturnSuccessfullyKitchen_WhenSearchingForValidId() {
        Long kitchenId = 1L;

        KitchenDTO kitchenDTO = kitchenService.findById(kitchenId);

        assertThat(kitchenDTO).isNotNull();
        assertEquals(kitchenDTO.getId(), kitchenId);
    }

    @Test
    @DisplayName("Should throw Exception When searching for Non-existent Id")
    void shouldThrowException_WhenSearchingForNonexistentId() {
        Long kitchenId = 100L;
        String expectedMessage = "Kitchen not found for this id 100";

        KitchenNotFoundException exception = assertThrows(KitchenNotFoundException.class, () -> {
            kitchenService.findById(kitchenId);
        });

        assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    @DisplayName("Should Delete Kitchen Successfully When Valid Id")
    void shouldDeleteKitchenSuccessfully_WhenValidId() {

        assertDoesNotThrow(() -> kitchenService.remove(5L));
        assertThrows(KitchenNotFoundException.class, () -> kitchenService.findById(5L));
    }

    @Test
    @DisplayName("Should Throw Exception When Kitchen Id to Nonexistent")
    void shouldThrowException_WhenKitchenIdToNonexistent() {
        Long kitchenId = 10003L;
        String expectedMessage = "There is no state with this code 10003";

        KitchenNotFoundException exception = assertThrows(KitchenNotFoundException.class, () -> {
            kitchenService.remove(kitchenId);
        });

        assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    @DisplayName("Should Throw Exception When Deleting Kitchen In Use")
    void shouldThrowException_WhenDeletingKitchenInUse() {
        Long kitchenId = 1L;

        EntityInUseException exception = assertThrows(EntityInUseException.class, () -> {
            kitchenService.remove(kitchenId);
        });

        String expectedMessage = "Kitchen cannot be excluded as it is in use";
        assertEquals(exception.getMessage(), expectedMessage);

    }

}
