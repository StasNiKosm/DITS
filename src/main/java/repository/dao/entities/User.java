package repository.dao.entities;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tuser")
@Getter @Setter @RequiredArgsConstructor @ToString
public class User {

    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int userId;

    @Column(name = "firstName", length = 30)
    private String firstName;

    @Column(name = "lastName", length = 30)
    private String lastName;

    @Column(name = "login", length = 30)
    private String login;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "role", length = 255)
    private String role;

//    @OneToMany( cascade = CascadeType.REMOVE)
//    @ToString.Exclude private Set<Statistic> statistic = Collections.emptySet();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, login, password, role);
    }
}
