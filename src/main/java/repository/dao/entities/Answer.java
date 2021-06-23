package repository.dao.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return answerid == answer.answerid && correct == answer.correct && description.equals(answer.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerid, description, correct);
    }
}
