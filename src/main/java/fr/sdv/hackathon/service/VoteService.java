package fr.sdv.hackathon.service;

import fr.sdv.hackathon.model.Vote;
import fr.sdv.hackathon.model.User;
import fr.sdv.hackathon.repository.VoteRepository;
import fr.sdv.hackathon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
@Service
public class VoteService {

    private final MatchService matchService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    public Boolean add(Long userId, Boolean isLike) {
        User connectedUser = userService.getConnected();
        User likedUser = userRepository.findById(userId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        if(connectedUser.equals(likedUser)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        Vote vote = Vote.builder()
                .user1(connectedUser)
                .user2(likedUser)
                .isLike(isLike)
                .build();

        voteRepository.save(vote);

        return matchService.hasMatch(vote);
    }

}
