package fr.sdv.hackathon.controller;

import fr.sdv.hackathon.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("image")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{userId}")
    public List<ResponseEntity<byte[]>> getAll(@PathVariable Long userId) {
        return imageService.getAll(userId).stream()
                .map(image -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                        .body(image.getData()))
                .toList();
    }

    @PostMapping()
    public Boolean add(@RequestBody MultipartFile file) {
        return imageService.store(file);
    }

}
