package com.joelmaciel.food.domain.jpa;

import com.joelmaciel.food.FoodApiApplication;
import com.joelmaciel.food.domain.model.City;
import com.joelmaciel.food.domain.repository.CityRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class QueryMainCity {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CityRepository cityRepository = applicationContext.getBean(CityRepository.class);

        List<City> cities = cityRepository.findAll();

        for (City city : cities) {
            System.out.printf("%s - %s\n", city.getName(), city.getState().getName());
        }
    }

}
