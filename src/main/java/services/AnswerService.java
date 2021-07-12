package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.dao.entities.Answer;
import repository.dao.entities.Literature;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.LazyManager;

import java.util.List;

@Service
public class AnswerService {

    private AnswerServiceFacade lazyInstance;

    private AnswerServiceFacade eagerInstance;

    @Autowired
    public void setLazyInstance(LazyManager<Answer> answerLazyManager) {
        this.lazyInstance = new AnswerServiceFacade(answerLazyManager);
    }

    @Autowired
    public void setEagerInstance(EagerManager<Answer> answerEagerManager) {
        this.eagerInstance = new AnswerServiceFacade(answerEagerManager);
    }

    public AnswerServiceFacade getEagerInstance() {
        return eagerInstance;
    }

    public AnswerServiceFacade getLazyInstance() {
        return lazyInstance;
    }

    public static class AnswerServiceFacade {

        private LazyManager<Answer> manager;

        private AnswerServiceFacade(LazyManager<Answer> manager) {
            this.manager = manager;
        }

        public Answer getAnswerById(int id) {
            return manager.read(id);
        }

        public List<Answer> getCorrectAnswerFromQuestion(int id) {
            return manager.executeSql("From Answer where questionid = " + id + " and correct = 1");
        }

        public List<Answer> getAllAnswerByQuestionId(int questionId) {
            return manager.executeSql("From Answer where questionid = " + questionId);
        }

        public void createAnswer(Answer answer){
            manager.create(answer);
        }

        public void updateAnswerById(int answerId, String answerDescription, int correct){
            Answer answer = manager.read(answerId);
            answer.setDescription(answerDescription);
            answer.setCorrect(correct);
            manager.update(answer);
        }

        public void deleteAnswerById(int answerId){
            manager.delete(manager.read(answerId));
        }

        public boolean containsAnswerById(int answerId){
            return manager.read(answerId) != null;
        }

    }

}
