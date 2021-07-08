package repository.dao.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "test")
@Getter @Setter @RequiredArgsConstructor @ToString
public class Test {

    @Column(name = "testid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int testId;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "name", length = 50)
    private String name;

    @ManyToOne(optional = false, fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "topicid")
    private Topic topic;

    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude private Set<Question> questions = Collections.emptySet();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Test)) return false;
        Test test = (Test) o;
        return Objects.equals(description, test.description) && Objects.equals(name, test.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, name);
    }


}
