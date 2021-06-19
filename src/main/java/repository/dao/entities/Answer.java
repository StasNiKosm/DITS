package repository.dao.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "answer")
@Getter @Setter @RequiredArgsConstructor @ToString
public class Answer {

    @Column(name = "answerid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int answerid;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "correct")
    private int correct;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "questionid")
    private Question question;

}
