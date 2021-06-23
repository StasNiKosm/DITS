package repository.dao.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "link")
@Getter @Setter @RequiredArgsConstructor @ToString
public class Link {

    @Column(name = "linkid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int linkid;

    @Column(name = "link", length = 255)
    private String link;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "literatureid")
    private Literature literature;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;
        Link link1 = (Link) o;
        return link.equals(link1.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }
}
