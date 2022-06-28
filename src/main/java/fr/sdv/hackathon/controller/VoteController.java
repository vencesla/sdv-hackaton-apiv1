package fr.sdv.hackathon.controller;

import fr.sdv.hackathon.model.Vote;
import fr.sdv.hackathon.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("like")
public class VoteController {

    private final VoteService voteService;

    @PostMapping()
    public Vote add(@RequestBody Long userId, @RequestBody Boolean isLike) {
        return voteService.add(userId, isLike);
    }

}
