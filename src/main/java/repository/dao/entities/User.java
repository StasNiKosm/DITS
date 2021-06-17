package repository.dao.entities;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "tuser")
@Getter @Setter @RequiredArgsConstructor @ToString
public class User {

    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    int userId;

    @Column(name = "firstName", length = 30)
    String firstName;

    @Column(name = "lastName", length = 30)
    String lastName;

    @Column(name = "login", length = 30)
    String login;

    @Column(name = "password", length = 255)
    String password;

    @Column(name = "role", length = 255)
    String role;
}
