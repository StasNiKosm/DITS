package dao.entities;

import javax.persistence.*;

@Entity
@Table(name = "test_table")
public class Test {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column(length = 25)
    private String name;

    public Test(String name) {
        this.name = name;
    }

    public Test() {

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
}
