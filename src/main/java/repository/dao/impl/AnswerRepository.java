package repository.dao.impl;

import org.springframework.stereotype.Repository;
import repository.dao.entities.Answer;

@Repository
public class AnswerRepository extends AbstractRepository<Answer>{

    public AnswerRepository() {
        super(Answer.class);
    }

}
