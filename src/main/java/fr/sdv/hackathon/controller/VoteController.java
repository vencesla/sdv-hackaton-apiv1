package fr.sdv.hackathon.controller;

import fr.sdv.hackathon.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("vote")
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/{userId}/{isLike}")
    public Boolean add(@PathVariable Long userId, @PathVariable Boolean isLike) {
        return voteService.add(userId, isLike);
    }

}
