package dao.entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "literature")
public class Literature {

    @Column(name = "literatureid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    int literatureId;

    @Column(name = "description", length = 100)
    String description;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "questionid")
    Question question;

    public Literature() {

    }

    public Literature(int id, String description, Question question) {
        this.literatureId = id;
        this.description = description;
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public int getLiteratureId() {
        return literatureId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLiteratureId(int literatureId) {
        this.literatureId = literatureId;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
