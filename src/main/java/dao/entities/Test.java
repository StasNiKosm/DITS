package dao.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "test")
@Getter @Setter @RequiredArgsConstructor @ToString
public class Test {

    @Column(name = "testid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    int testId;

    @Column(name = "description", length = 100)
    String description;

    @Column(name = "name", length = 20)
    String name;

    @ManyToOne(optional = false, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "topicid")
    Topic topic;

    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude private Set<Question> questions;

}
