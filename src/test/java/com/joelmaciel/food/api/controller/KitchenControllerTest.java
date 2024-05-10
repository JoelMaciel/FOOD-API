package com.joelmaciel.food.api.controller;

import static io.restassured.RestAssured.*;

import com.joelmaciel.food.api.dto.request.KitchenRequestDTO;
import com.joelmaciel.food.domain.model.Kitchen;
import com.joelmaciel.food.domain.repository.KitchenRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.yml")
class KitchenControllerTest {

    public static final int KITCHEN_ID_NONEXISTENT = 20;
    public static final int KITCHEN_ID_EXISTENT = 2;
    public static final String MSG_KITCHEN_IN_USE = "Kitchen cannot be excluded as it is in use";
    @LocalServerPort
    private int port;

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/api/kitchens";

        flyway.migrate();
    }

    @Test
    @DisplayName("Should Return Status 200 When Querying Kitchens")
    void shouldReturnStatus200_WhenQueryingKitchens() {
        given()
                .accept(ContentType.JSON)
              .when()
                .get()
              .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Should Contain Five Kitchens When Querying Kitchens")
    void shouldToContain4Kitchens_WhenQueryingKitchens() {
        int numberOfRegisteredKitchens = 5;
        given()
                .accept(ContentType.JSON)
              .when()
                .get()
              .then()
                .body("", hasSize(numberOfRegisteredKitchens))
                .body("name", hasItems("Brazilian", "Argentina"));
    }

    @Test
    @DisplayName("Should Return Status 201 When Registering Kitchen Successfully")
    void shouldReturnStatus201_WhenRegisteringKitchenSuccessfully() {
        KitchenRequestDTO kitchenRequestDTO = getKitchenRequestDTO();

        given()
                .body(kitchenRequestDTO)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
              .when()
                .post()
              .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Should Return Status 400 When Registering Kitchen Unnamed")
    void shouldReturnStatus400_WhenRegisteringKitchenUnnamed() {
        KitchenRequestDTO noNameKitchen = getNoNameKitchen();

        given()
                .body(noNameKitchen)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
             .when()
                .post()
             .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo("Invalid data"));
    }

    @Test
    @DisplayName("Should Return Status 200 When Consult Existing Kitchen")
    void shouldReturnStatus200_WhenConsultExistingKitchen() {
        given()
                .pathParam("kitchenId", KITCHEN_ID_EXISTENT)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
              .when()
                .get("/{kitchenId}")
              .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo("Indian"));
    }

    @Test
    @DisplayName("Should Return Status 404 When Consult Nonexistent Kitchen")
    void shouldReturnStatus404_WhenConsultNonexistentKitchen() {
        given()
                .pathParam("kitchenId", KITCHEN_ID_NONEXISTENT)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
              .when()
                .get("/{kitchenId}")
              .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
    @Test
    @DisplayName("Should Update Kitchen Successfully When Existing Kitchen Name")
    void shouldUpdateKitchenSuccessfully_WhenExistingKitchenName() {
        KitchenRequestDTO updateKitchen = getUpdateKitchen();
        String nameKitchen = "English";

        given()
                .pathParam("kitchenId", KITCHEN_ID_EXISTENT)
                .body(updateKitchen)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put("/{kitchenId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo(nameKitchen));
    }

    @Test
    @DisplayName("Should throw KitchenNotFoundException when updating non-existent kitchen")
    void shouldThrowKitchenNotFoundExceptionWhenUpdatingNonExistentKitchen() {
        KitchenRequestDTO updateKitchen = getUpdateKitchen();
        String expectedMessageDetail = "Kitchen not found for this id 20";

        given()
                .pathParam("kitchenId", KITCHEN_ID_NONEXISTENT)
                .body(updateKitchen)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put("/{kitchenId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("detail", equalTo(expectedMessageDetail));
    }


    @Test
    @DisplayName("Should launch StatusCode 409 When Deleting Kitchen in Use")
    void shouldLaunchStatusCode409_WhenDeletingKitchenInUse() {
        given()
                .pathParam("kitchenId", KITCHEN_ID_EXISTENT)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
             .when()
                .delete("/{kitchenId}")
             .then()
                .statusCode(HttpStatus.CONFLICT.value())
                .body("detail", equalTo(MSG_KITCHEN_IN_USE));

    }

    @Test
    @DisplayName("Should Delete Kitchen Successfully When KitchenId is Valid")
    void shouldDeleteKitchenSuccessfully_WhenKitchenIdIsValid() {
        Kitchen kitchen = getKitchen();

        given()
                .pathParam("kitchenId", kitchen.getId())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
             .when()
                .delete("/{kitchenId}")
             .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }


    private Kitchen getKitchen() {
        Kitchen kitchen = new Kitchen();
        kitchen.setName("Norway");
       return kitchenRepository.save(kitchen);
    }

    private KitchenRequestDTO getKitchenRequestDTO() {
        KitchenRequestDTO kitchenRequestDTO = new KitchenRequestDTO();
        kitchenRequestDTO.setName("Italian");
        return kitchenRequestDTO;
    }

    private KitchenRequestDTO getNoNameKitchen() {
        KitchenRequestDTO kitchenRequestDTO = new KitchenRequestDTO();
        kitchenRequestDTO.setName(null);
        return kitchenRequestDTO;
    }
    private KitchenRequestDTO getUpdateKitchen() {
        KitchenRequestDTO kitchenRequestDTO = new KitchenRequestDTO();
        kitchenRequestDTO.setName("English");
        return kitchenRequestDTO;
    }

}