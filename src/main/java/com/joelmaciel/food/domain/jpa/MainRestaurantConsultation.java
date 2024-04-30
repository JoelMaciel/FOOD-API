package com.joelmaciel.food.domain.jpa;

import com.joelmaciel.food.FoodApiApplication;
import com.joelmaciel.food.domain.model.Kitchen;
import com.joelmaciel.food.domain.model.Restaurant;
import com.joelmaciel.food.domain.repository.KitchenRepository;
import com.joelmaciel.food.domain.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.List;

@Slf4j
public class MainRestaurantConsultation {

    public static void main(String[] args) {
        org.springframework.context.ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);
        List<Restaurant> restaurants = restaurantRepository.findAll();
        for (Restaurant restaurant : restaurants) {
            log.info("Restaurant Name : {} , Kitchen : {} " , restaurant.getName(), restaurant.getKitchen().getName());
        }

    }
}
