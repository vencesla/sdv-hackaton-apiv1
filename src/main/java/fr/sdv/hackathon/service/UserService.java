package fr.sdv.hackathon.service;

import fr.sdv.hackathon.model.Vote;
import fr.sdv.hackathon.model.User;
import fr.sdv.hackathon.repository.VoteRepository;
import fr.sdv.hackathon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    public User getConnected() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByEmail(currentPrincipalName)
                .orElse(null);
    }

    public boolean add(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("The email " + email + " is already used.");
        }

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        userRepository.save(user);
        return true;
    }

    public User getRandom() {
        User connectedUser = getConnected();
        List<Vote> votes = voteRepository.findAllByUser1(connectedUser);

        List<User> users = userRepository.findAll().stream()
                .filter(user ->
                        !user.getId().equals(connectedUser.getId())
                        && votes.stream().noneMatch(vote -> vote.getUser2().equals(user)))
                .toList();

        if(users.isEmpty()) return null;

        return users.get((int)(Math.random() * users.size()));
    }

    public User update(User user) {
        User connectedUser = getConnected();

        User foundedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        if(!connectedUser.equals(foundedUser)) {
            throw new HttpClientErrorException(HttpStatus.NOT_MODIFIED);
        }

        user.setPassword(connectedUser.getPassword());

        return userRepository.save(user);
    }

}
