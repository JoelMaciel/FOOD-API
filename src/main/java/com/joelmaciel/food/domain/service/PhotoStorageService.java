package com.joelmaciel.food.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface PhotoStorageService {

    void storePhoto(NewPhoto newPhoto);

    void remove(String fileName);

    InputStream recoverPhoto(String fileName);

    default String generateFileName(String originalName) {
        return UUID.randomUUID().toString() + "_" + originalName;
    }

    @Builder
    @Getter
    class NewPhoto {
        private String fileName;
        private InputStream inputStream;
    }
}
