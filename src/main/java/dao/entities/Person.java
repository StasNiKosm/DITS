package dao.entities;

import javax.persistence.*;

@Entity
@Table(name = "test_table")
public class Person {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column(length = 25)
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public Person() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
