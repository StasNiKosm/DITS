package repository.dao.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "statistic")
@Getter @Setter @RequiredArgsConstructor @ToString
public class Statistic {

    @Column(name = "statisticid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int statisticid;

    @Column(name = "date")
    private Date date;

    @Column(name = "correct")
    private int correct;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "questionid")
    private Question question;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "userid")
    private User user;

}
