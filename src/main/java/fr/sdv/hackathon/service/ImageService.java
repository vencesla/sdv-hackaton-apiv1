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
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public Boolean store(MultipartFile file) {
        if(file == null || file.getOriginalFilename() == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_MODIFIED);
        }

        try {
            User connectedUser = userService.getConnected();
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String extension = file.getContentType();

            imageRepository.save(Image.builder()
                    .user(connectedUser)
                    .name(fileName)
                    .extension(extension)
                    .data(file.getBytes())
                    .build());

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new HttpClientErrorException(HttpStatus.NOT_MODIFIED);
    }

    public List<Image> getAll(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        return imageRepository.findAllByUser(user);
    }

}
