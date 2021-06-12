package dao.entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "test")
public class Test {

    @Column(name = "testid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    int testId;

    @Column(name = "description", length = 100)
    String description;

    @Column(name = "name", length = 20)
    String name;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "topicid")
    Topic topic;

    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY)
    private List<Question> questions;

    public Test() {

    }

    public Test(int id, String description, String name, Topic topic) {
        this.testId = id;
        this.name = name;
        this.description = description;
        this.topic = topic;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        if (questions == null)
            return Collections.emptyList();
        return questions;
    }

    public String getName() {
        return name;
    }

    public Topic getTopic() {
        return topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Override
    public String toString() {
        return "Test{" +
                "testId=" + testId +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", topic=" + topic.getTopicId() +
                ", questions=" + questions +
                '}';
    }
}
