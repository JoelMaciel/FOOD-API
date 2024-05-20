package com.joelmaciel.food.domain.service.impl.unit;

import com.joelmaciel.food.api.dto.converter.RestaurantConverter;
import com.joelmaciel.food.api.dto.request.RestaurantRequestDTO;
import com.joelmaciel.food.api.dto.response.RestaurantDTO;
import com.joelmaciel.food.domain.exception.RestaurantNotFoundException;
import com.joelmaciel.food.domain.model.Kitchen;
import com.joelmaciel.food.domain.model.Restaurant;
import com.joelmaciel.food.domain.repository.RestaurantRepository;
import com.joelmaciel.food.domain.service.KitchenService;
import com.joelmaciel.food.domain.service.impl.RestaurantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class RestaurantServiceImplTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private KitchenService kitchenService;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    private Restaurant restaurant;
    private List<Restaurant> restaurants;
    private RestaurantRequestDTO restaurantRequestDTO;
    private Kitchen kitchenTwo;


    @BeforeEach
    void setUp() {
        openMocks(this);
        restaurant = getRestaurant();
        restaurants = Arrays.asList(getRestaurant(), getRestaurantTwo());
        restaurantRequestDTO = getRestaurantRequestDTO();
        kitchenTwo = getKitchenTwo();
    }

    @Test
    @DisplayName("Should Return Successful Restaurant List")
    void shouldReturnSuccessfulRestaurantList() {
        when(restaurantRepository.findAll()).thenReturn(restaurants);

        List<RestaurantDTO> restaurantDTOS = restaurantService.findAll();

        assertEquals(2, restaurantDTOS.size());
    }

    @Test
    @DisplayName("Should Return Restaurant Successfully When Querying by Id")
    void shouldReturnRestaurantSuccessfully_WhenQueryingById() {
        Long restaurantId = 1L;

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        RestaurantDTO restaurantDTO = restaurantService.findById(restaurantId);

        assertNotNull(restaurantDTO);
        assertEquals(restaurant.getId(), restaurantDTO.getId());
        assertEquals(restaurant.getName(), restaurantDTO.getName());
        assertEquals(restaurant.getFreightRate(), restaurantDTO.getFreightRate());
        assertEquals(restaurant.getKitchen().getId(), restaurantDTO.getKitchen().getId());

        verify(restaurantRepository, times(1)).findById(restaurantId);
    }

    @Test
    @DisplayName("Should Throw Exception When Querying Restaurant with Non-existent Id")
    void shouldThrowException_WhenQueryingRestaurantWithNonExistentId() {
        Long restaurantNonExistentId = 80L;
        when(restaurantRepository.findById(restaurantNonExistentId)).thenReturn(Optional.empty());

        RestaurantNotFoundException exception = assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantService.findById(restaurantNonExistentId);
        });

        String expectedMessage = "Restaurant not found for this id 80";
        assertEquals(exception.getMessage(), expectedMessage);

        verify(restaurantRepository, times(1)).findById(restaurantNonExistentId);
    }

    @Test
    @DisplayName("ShouldMust Save Restaurant Successfully When Executing the Save Method")
    void shouldSaveRestaurantSuccessfully_WhenExecutingTheSaveMethod() {

        Restaurant restaurantToSave = RestaurantConverter.toEntity(restaurantRequestDTO);

        when(kitchenService.optionalKitchen(kitchenTwo.getId())).thenReturn(kitchenTwo);
        when(restaurantRepository.save(restaurantToSave)).thenReturn(restaurantToSave);

        RestaurantDTO restaurantDTO = restaurantService.save(restaurantRequestDTO);

        assertNotNull(restaurantDTO);
        assertEquals(restaurantRequestDTO.getName(), restaurantDTO.getName());
        assertEquals(restaurantRequestDTO.getFreightRate(), restaurantDTO.getFreightRate());

        verify(restaurantRepository, times(1)).save(restaurantToSave);
    }


    private RestaurantRequestDTO getRestaurantRequestDTO() {
        return RestaurantRequestDTO.builder()
                .name("Restaurant Test")
                .freightRate(BigDecimal.valueOf(23))
                .kitchenId(1L)
                .build();
    }

    private Restaurant getRestaurant() {
        return Restaurant.builder()
                .id(1L)
                .name("Restaurant Test")
                .freightRate(BigDecimal.valueOf(10))
                .kitchen(getKitchen())
                .build();
    }

    private Restaurant getRestaurantTwo() {
        return Restaurant.builder()
                .id(2L)
                .name("Restaurant Test Two")
                .freightRate(BigDecimal.valueOf(15))
                .kitchen(getKitchenTwo())
                .build();
    }

    private Kitchen getKitchen() {
        return Kitchen.builder()
                .id(1L)
                .name("Kitchen test")
                .build();
    }

    private Kitchen getKitchenTwo() {
        return Kitchen.builder()
                .id(1L)
                .name("Kitchen test")
                .build();
    }

}

























