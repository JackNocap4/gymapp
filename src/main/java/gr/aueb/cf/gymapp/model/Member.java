package gr.aueb.cf.gymapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "members")
public class Member extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String gymCode;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDateTime membershipStartDate;

    @Column(nullable = false)
    private LocalDateTime membershipEndDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "member_workout_programs",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "workout_program_id")
    )
    private Set<WorkOutProgram> workoutPrograms = new HashSet<>();

    public Set<WorkOutProgram> getAllWorkoutPrograms() {
        if (workoutPrograms == null) workoutPrograms = new HashSet<>();
        return Collections.unmodifiableSet(workoutPrograms);
    }

    public void addWorkoutProgram(WorkOutProgram workoutProgram) {
        if (workoutPrograms == null) workoutPrograms = new HashSet<>();
        workoutPrograms.add(workoutProgram);
        workoutProgram.getMembers().add(this);
    }

    public void removeWorkoutProgram(WorkOutProgram workoutProgram) {
        workoutPrograms.remove(workoutProgram);
        workoutProgram.getMembers().remove(this);
    }

    public boolean hasWorkoutPrograms() {
        return workoutPrograms != null && !workoutPrograms.isEmpty();
    }
}