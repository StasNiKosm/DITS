package repository.dao.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "question")
@Getter @Setter @RequiredArgsConstructor @ToString
public class Question {

    @Column(name = "questionid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int questionId;

    @Column(name = "description", length = 100)
    private String description;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "testid")
    private  Test test;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude private Set<Literature> literature = Collections.emptySet();

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude private Set<Answer> answers = Collections.emptySet();

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude private Set<Statistic> statistic = Collections.emptySet();

}