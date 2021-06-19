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

}
