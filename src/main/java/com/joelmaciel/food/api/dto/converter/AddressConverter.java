package com.joelmaciel.food.api.dto.converter;

import com.joelmaciel.food.api.dto.request.AddressRequestDTO;
import com.joelmaciel.food.api.dto.response.AddressDTO;
import com.joelmaciel.food.domain.model.Address;
import com.joelmaciel.food.domain.model.City;

public class AddressConverter {

    private AddressConverter() {};

    public static AddressDTO toDTO(Address address) {
        return AddressDTO.builder()
                .street(address.getStreet())
                .district(address.getDistrict())
                .number(address.getNumber())
                .zipCode(address.getZipCode())
                .complement(address.getComplement())
                .city(CityConverter.toDTO(address.getCity()))
                .build();
    }

    public static Address toEntity(AddressRequestDTO addressRequestDTO, City city) {
        return Address.builder()
                .zipCode(addressRequestDTO.getZipCode())
                .street(addressRequestDTO.getStreet())
                .number(addressRequestDTO.getNumber())
                .complement(addressRequestDTO.getComplement())
                .district(addressRequestDTO.getDistrict())
                .city(city)
                .build();
    }
}
