package com.joelmaciel.food.infra.service.storage;

import com.joelmaciel.food.domain.service.PhotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PhotoStorageServiceImpl implements PhotoStorageService {

    @Value("${food.storage.location.photos-directory}")
    private Path photoDirectory;

    @Override
    public void storePhoto(NewPhoto newPhoto) {
        try {
            Path filePath = getPathFile(newPhoto.getFileName());

            FileCopyUtils.copy(newPhoto.getInputStream(), Files.newOutputStream(filePath));
        } catch (Exception e) {
            throw new StorageException("Unable to store the file", e);
        }
    }

    @Override
    public void remove(String fileName) {
        try {
            Path filePath = getPathFile(fileName);
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new StorageException("Unable to delete file", e);
        }

    }

    @Override
    public InputStream recoverPhoto(String fileName) {
        try {
            Path filePath = getPathFile(fileName);
            return Files.newInputStream(filePath);
        } catch (Exception e) {
            throw new StorageException("Unable to retrieve file.", e);
        }
    }

    private Path getPathFile(String fileName) {
        return photoDirectory.resolve(Path.of(fileName));
    }
}
