package dao.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "literature")
@Getter @Setter @RequiredArgsConstructor @ToString
public class Literature {

    @Column(name = "literatureid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    int literatureId;

    @Column(name = "description", length = 100)
    String description;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "questionid")
    Question question;

}
