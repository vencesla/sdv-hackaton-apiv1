package fr.sdv.hackathon.controller;

import fr.sdv.hackathon.model.User;
import fr.sdv.hackathon.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("match")
public class MatchController {

    private final MatchService matchService;

    @GetMapping()
    public List<User> getAll() {
        return matchService.getAll();
    }

}
