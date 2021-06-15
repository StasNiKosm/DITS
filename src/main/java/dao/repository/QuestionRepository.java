package dao.repository;

import dao.entities.Question;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionRepository extends AbstractRepository<Question> {

    public QuestionRepository() {
        super(Question.class);
    }

}
