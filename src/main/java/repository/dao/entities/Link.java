package repository.dao.entities;

import lombok.*;

import javax.persistence.*;

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

}
