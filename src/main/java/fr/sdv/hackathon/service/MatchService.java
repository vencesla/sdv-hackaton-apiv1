package fr.sdv.hackathon.service;

import fr.sdv.hackathon.model.User;
import fr.sdv.hackathon.model.Vote;
import fr.sdv.hackathon.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchService {

    private final UserService userService;
    private final VoteRepository voteRepository;

    public List<User> getAll() {
        User connectedUser = userService.getConnected();

        List<Vote> votes1 = voteRepository.findAllByUser1(connectedUser).stream()
                .filter(Vote::getIsLike)
                .toList();

        List<Vote> votes2 = voteRepository.findAllByUser2(connectedUser).stream()
                .filter(Vote::getIsLike)
                .toList();

        return votes1.stream()
                .map(Vote::getUser2)
                .filter(user2 -> votes2.stream().anyMatch(vote2 -> user2.equals(vote2.getUser1())))
                .toList();
    }

}
