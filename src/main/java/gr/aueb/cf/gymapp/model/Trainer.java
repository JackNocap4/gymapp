package gr.aueb.cf.gymapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "trainers")
public class Trainer extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;s

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String speciality;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "trainer")
    private Set<WorkOutProgram> workoutPrograms = new HashSet<>();
}