package fr.sdv.hackathon.service;

import fr.sdv.hackathon.model.Image;
import fr.sdv.hackathon.model.User;
import fr.sdv.hackathon.model.Vote;
import fr.sdv.hackathon.repository.ImageRepository;
import fr.sdv.hackathon.repository.UserRepository;
import fr.sdv.hackathon.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public List<String> get(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        return imageRepository.findAllByUser(user).stream()
                .map(Image::getContent)
                .toList();
    }

    public Boolean put(String content) {
        User connectedUser = userService.getConnected();

        int count = imageRepository.findAllByUser(connectedUser).size();
        if(count >= 3) {
            throw new RuntimeException("A user can have 3 images maximum.");
        }

        imageRepository.save(Image.builder()
                .user(connectedUser)
                .content(content)
                .build());

        return true;
    }

}
