package repository.dao.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topic)) return false;
        Topic topic = (Topic) o;
        return description.equals(topic.description) && name.equals(topic.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, name);
    }
}
