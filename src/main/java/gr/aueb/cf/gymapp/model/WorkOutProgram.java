package gr.aueb.cf.gymapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "workout_programs")
public class WorkOutProgram extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @Getter(AccessLevel.PROTECTED)
    @ManyToMany(mappedBy = "workoutPrograms", fetch = FetchType.LAZY)s
    private Set<Member> members = new HashSet<>();

    public Set<Member> getAllMembers() {
        return Collections.unmodifiableSet(members);
    }

    public void addMember(Member member) {
        if (members == null) members = new HashSet<>();
        members.add(member);
        member.getWorkoutPrograms().add(this);
    }

    public void removeMember(Member member) {
        if (members == null) return;
        members.remove(member);
        member.getWorkoutPrograms().remove(this);
    }
}