package org.arzimanoff.marketplace.controllers;

import lombok.RequiredArgsConstructor;
import org.arzimanoff.marketplace.exceptions.ImageNotFoundException;
import org.arzimanoff.marketplace.exceptions.ProductNotFoundException;
import org.arzimanoff.marketplace.models.ProductImage;
import org.arzimanoff.marketplace.repositories.ImageRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;
    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id){
        var img = imageRepository.findById(id);
        if (img.isEmpty()) {
            throw new ImageNotFoundException(id);
        }
        ProductImage image = img.get();
        return ResponseEntity.ok()
                .header("filename", image.getOriginalFilename())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
