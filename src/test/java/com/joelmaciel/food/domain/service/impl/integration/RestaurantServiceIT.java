package com.joelmaciel.food.domain.service.impl.integration;

import com.joelmaciel.food.api.dto.request.RestaurantRequestDTO;
import com.joelmaciel.food.api.dto.response.RestaurantDTO;
import com.joelmaciel.food.domain.exception.BusinessException;
import com.joelmaciel.food.domain.exception.KitchenNotFoundException;
import com.joelmaciel.food.domain.exception.RestaurantNotFoundException;
import com.joelmaciel.food.domain.service.RestaurantService;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestPropertySource("/application-test.yml")
class RestaurantServiceIT {

    @Autowired
    private RestaurantService restaurantService;


    @Autowired
    private Flyway flyway;

    private RestaurantRequestDTO restaurantRequestDTO;
    private RestaurantRequestDTO restaurantRequestDTOInvalid;
    private RestaurantRequestDTO restaurantNonexistentKitchenId;


    @BeforeEach
    void setUp() {
        restaurantRequestDTO = getRestaurantRequestDTO();
        restaurantRequestDTOInvalid = getRestaurantWhitInvalidData();
        restaurantNonexistentKitchenId = getUpdateRestaurantNonexistentKitchenId();

        flyway.migrate();
    }

    @Test
    @DisplayName("Should List All Restaurants Successfully")
    void shouldListAllRestaurantsSuccessfully() {
        List<RestaurantDTO> restaurantDTOList = restaurantService.findAll();

        assertThat(restaurantDTOList).isNotEmpty();

        int quantityRestaurants = 6;
        assertThat(restaurantDTOList).hasSize(quantityRestaurants);
        assertNotNull(restaurantDTOList);

    }

    @Test
    @DisplayName("Should Save Restaurant Successfully When Valid Data Is Provided")
    void shouldSaveSuccessfulRestaurant() {
        RestaurantDTO restaurantDTO = restaurantService.save(restaurantRequestDTO);

        assertThat(restaurantDTO).isNotNull();
        assertThat(restaurantDTO.getId()).isNotNull();

        String nameRestaurant = "Street food";
        assertThat(restaurantDTO.getName()).isEqualTo(nameRestaurant);
    }

    @Test
    @DisplayName("Should Throw An Exception When Registering Restaurant Without a Name")
    void shouldThrowAnException_WhenRegisteringRestaurantWithoutAName() {

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            restaurantService.save(restaurantRequestDTOInvalid);
        });

        assertThat(exception).isNotNull();
    }

    @Test
    @DisplayName("Should Update Restaurant Successfully When Data Valid")
    void shouldUpdateRestaurantSuccessfully_WhenDataValid() {
        Long restaurantId = 1L;

        RestaurantRequestDTO updateRestaurant = getUpdateRestaurant();

        RestaurantDTO restaurantDTO = restaurantService.update(restaurantId, updateRestaurant);

        assertNotNull(restaurantDTO);
        assertEquals(restaurantDTO.getName(), updateRestaurant.getName());
        assertEquals(restaurantDTO.getFreightRate(), updateRestaurant.getFreightRate());
    }

    @Test
    @DisplayName("Should thrown Exception  When Updating Restaurant With Invalid KitchenId")
    void shouldThrownException_WhenUpdatingRestaurantWithInvalidKitchenId() {
        Long restaurantId = 1L;

        KitchenNotFoundException exception = assertThrows(KitchenNotFoundException.class, () -> {
            restaurantService.update(restaurantId, restaurantNonexistentKitchenId);
        });

        assertNotNull(exception);

        String expectedMessage = "Kitchen not found for this id 15";
        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    @DisplayName("Should thrown Exception  When Updating Restaurant With Invalid RestaurantId")
    void shouldThrownException_WhenUpdatingRestaurantWithInvalidRestaurantId() {
        Long restaurantIdInvalid = 10L;

        RestaurantNotFoundException exception = assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantService.update(restaurantIdInvalid, restaurantRequestDTO);
        });

        assertNotNull(exception);

        String expectedMessage = "Restaurant not found for this id 10";
        assertEquals(expectedMessage, exception.getMessage());

    }

    private RestaurantRequestDTO getRestaurantRequestDTO() {
        return RestaurantRequestDTO.builder()
                .name("Street food")
                .freightRate(new BigDecimal(55))
                .kitchenId(2L)
                .build();
    }

    private RestaurantRequestDTO getRestaurantWhitInvalidData() {
        return RestaurantRequestDTO.builder()
                .name(null)
                .freightRate(new BigDecimal(18))
                .kitchenId(1L)
                .build();
    }

    private RestaurantRequestDTO getUpdateRestaurant() {
        return RestaurantRequestDTO.builder()
                .name("Chilean")
                .freightRate(new BigDecimal(21))
                .kitchenId(2L)
                .build();
    }

    private RestaurantRequestDTO getUpdateRestaurantNonexistentKitchenId() {
        return RestaurantRequestDTO.builder()
                .name("Chilean")
                .freightRate(new BigDecimal(21))
                .kitchenId(15L)
                .build();
    }

}
