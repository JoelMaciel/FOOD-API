package com.joelmaciel.food.domain.service.impl.unit;

import com.joelmaciel.food.api.dto.request.KitchenRequestDTO;
import com.joelmaciel.food.api.dto.response.KitchenDTO;
import com.joelmaciel.food.domain.exception.EntityInUseException;
import com.joelmaciel.food.domain.exception.KitchenNotFoundException;
import com.joelmaciel.food.domain.model.Kitchen;
import com.joelmaciel.food.domain.repository.KitchenRepository;
import com.joelmaciel.food.domain.service.impl.KitchenServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class KitchenServiceImplTest {

    @Mock
    private KitchenRepository kitchenRepository;

    @InjectMocks
    private KitchenServiceImpl kitchenService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Should Find All kitchens Successfully")
    void shouldFindAllKitchensSuccessfully() {
        Kitchen kitchen1 = new Kitchen();
        Kitchen kitchen2 = new Kitchen();
        List<Kitchen> kitchens = Arrays.asList(kitchen1, kitchen2);

        when(kitchenRepository.findAll()).thenReturn(kitchens);

        List<KitchenDTO> result = kitchenService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Should Find Kitchen by ID Successfully")
    void shouldFindKitchenByIdSuccessfully() {
        Kitchen kitchen = new Kitchen();
        kitchen.setId(1L);

        when(kitchenRepository.findById(anyLong())).thenReturn(Optional.of(kitchen));

        KitchenDTO result = kitchenService.findById(1L);

        assertEquals(1, result.getId());
    }

    @Test
    @DisplayName("Should Save Kitchen Successfully")
    void shouldSaveKitchenSuccessfully() {
        KitchenRequestDTO requestDTO = new KitchenRequestDTO();
        requestDTO.setName("Test Kitchen");

        Kitchen kitchen = new Kitchen();
        kitchen.setName(requestDTO.getName());

        when(kitchenRepository.save(any(Kitchen.class))).thenReturn(kitchen);

        KitchenDTO result = kitchenService.save(requestDTO);

        assertEquals(requestDTO.getName(), result.getName());
    }

    @Test
    @DisplayName("Should throw DataIntegrityViolationException when saving kitchen with null name")
    void shouldThrowDataIntegrityViolationExceptionWhenSavingKitchenWithNullName(){
        KitchenRequestDTO kitchenRequestDTO = new KitchenRequestDTO();
        kitchenRequestDTO.setName(null);

        when(kitchenRepository.save(any(Kitchen.class))).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> kitchenService.save(kitchenRequestDTO));
    }


    @Test
    @DisplayName("Should Update Kitchen Successfully")
    void shouldUpdateKitchenSuccessfully() {
        Kitchen kitchen = new Kitchen();
        kitchen.setId(1L);
        kitchen.setName("Joel");

        KitchenRequestDTO requestDTO = new KitchenRequestDTO();
        requestDTO.setName("Maciel");

        when(kitchenRepository.findById(anyLong())).thenReturn(Optional.of(kitchen));
        when(kitchenRepository.save(any(Kitchen.class))).thenReturn(kitchen);

        KitchenDTO result = kitchenService.update(1L, requestDTO);

        assertEquals(requestDTO.getName(), result.getName());
    }

    @Test
    @DisplayName("Should Delete Kitchen successfully")
    void shouldDeleteKitchenSuccessfully() {
        doNothing().when(kitchenRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> kitchenService.remove(1L));
    }

    @Test
    @DisplayName("Should Throw KitchenNotFoundException When Deleting Non-existent kitchen")
    void shouldThrowKitchenNotFoundExceptionWhenDeletingNonExistentKitchen() {
        doThrow(EmptyResultDataAccessException.class).when(kitchenRepository).deleteById(anyLong());

        assertThrows(KitchenNotFoundException.class, () -> kitchenService.remove(999L));
    }

    @Test
    @DisplayName("Should Throw EntityInUseException When Deleting kitchen in use")
    void shouldThrowEntityInUseExceptionWhenDeletingKitchenInUse() {
        doThrow(DataIntegrityViolationException.class).when(kitchenRepository).deleteById(anyLong());

        assertThrows(EntityInUseException.class, () -> kitchenService.remove(1L));
    }


    @Test
    @DisplayName("Should Throw EntityInUseException When Deleting kitchen in use")
    void shouldThrowEntityInUseException_WhenDeletingKitchenInUse() {
        doThrow(DataIntegrityViolationException.class).when(kitchenRepository).deleteById(anyLong());

        assertThrows(EntityInUseException.class, () -> kitchenService.remove(1L));
    }

}
