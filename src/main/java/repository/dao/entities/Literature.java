package repository.dao.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "literature")
@Getter @Setter @RequiredArgsConstructor @ToString
public class Literature {

    @Column(name = "literatureid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int literatureId;

    @Column(name = "description", length = 100)
    private String description;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "questionid")
    private Question question;

    @OneToMany(mappedBy = "literature", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude private Set<Link> links = Collections.emptySet();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Literature)) return false;
        Literature that = (Literature) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
