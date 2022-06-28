package fr.sdv.hackathon.controller;

import fr.sdv.hackathon.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("image")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{userId}")
    public List<String> get(@PathVariable Long userId) {
        return imageService.get(userId);
    }

    @PostMapping()
    public Boolean add(@RequestBody String content) {
        return imageService.put(content);
    }

}
