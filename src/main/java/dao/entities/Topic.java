package dao.entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "topic")
public class Topic {

    @Column(name = "topicid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    int topicId;

    @Column(name = "description", length = 100)
    String description;

    @Column(name = "name", length = 20)
    String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
    private List<Test> tests;

    public Topic() {

    }

    public Topic(int id, String description, String name) {
        this.topicId = id;
        this.name = name;
        this.description = description;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public List<Test> getTests() {
        if (tests == null)
            return Collections.emptyList();
        return tests;
    }

    public String getName() {
        return name;
    }

    public int getTopicId() {
        return topicId;
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

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicId=" + topicId +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", tests=" + tests +
                '}';
    }
}
