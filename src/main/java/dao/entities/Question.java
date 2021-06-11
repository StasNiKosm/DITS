package dao.entities;

import javax.persistence.*;
import java.util.Queue;

@Entity
public class Question {
    @Column(name = "questionid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    int questionId;

    @Column(name = "description", length = 100)
    String description;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "testid")
    Test test;

    public Question() {

    }

    public Question(int id, String description, Test test) {
        this.questionId = id;
        this.description = description;
        this.test = test;
    }

    public String getDescription() {
        return description;
    }

    public int getQuestionId() {
        return questionId;
    }

    public Test getTest() {
        return test;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", description='" + description + '\'' +
                ", test=" + test.getTestId() +
                '}';
    }
}
