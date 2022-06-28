package fr.sdv.hackathon.repository;

import fr.sdv.hackathon.model.Image;
import fr.sdv.hackathon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByUser(User user);

}
