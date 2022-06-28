package fr.sdv.hackathon.repository;

import fr.sdv.hackathon.model.Vote;
import fr.sdv.hackathon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findAllByUser1(User user);

}
