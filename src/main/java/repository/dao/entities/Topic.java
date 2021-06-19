package repository.dao.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "topic")
@Getter @Setter @RequiredArgsConstructor @ToString
public class Topic {

    @Column(name = "topicid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int topicId;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "name", length = 20)
    private String name;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude private Set<Test> tests = Collections.emptySet();

}
