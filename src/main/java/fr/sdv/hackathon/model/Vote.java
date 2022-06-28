package fr.sdv.hackathon.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_1_id")
    private User user1;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_2_id")
    private User user2;

    @NotNull
    private Boolean isLike;

}
