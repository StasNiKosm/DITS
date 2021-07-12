package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.dao.entities.Literature;
import repository.dao.entities.Question;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.LazyManager;

import java.util.List;
import java.util.Set;

@Service
public class QuestionService {

    private QuestionServiceFacade lazyInstance;

    private QuestionServiceFacade eagerInstance;

    @Autowired
    public void setLazyInstance(LazyManager<Question> questionLazyManager) {
        this.lazyInstance = new QuestionServiceFacade(questionLazyManager);
    }

    @Autowired
    public void setEagerInstance(EagerManager<Question> questionEagerManager) {
        this.eagerInstance = new QuestionServiceFacade(questionEagerManager);
    }

    public QuestionServiceFacade getEagerInstance() {
        return eagerInstance;
    }

    public QuestionServiceFacade getLazyInstance() {
        return lazyInstance;
    }

    public static class QuestionServiceFacade {

        private LazyManager<Question> manager;

        private QuestionServiceFacade(LazyManager<Question> manager) {
            this.manager = manager;
        }

        public void createQuestion(Question question){
            manager.create(question);
        }

        public void updateQuestionById(int questionId, String questionDescription){
            Question question = manager.read(questionId);
            question.setDescription(questionDescription);
            manager.update(question);
        }

        public void deleteQuestionById(int questionId){
            manager.delete(manager.read(questionId));
        }

        public boolean containsQuestionById(int questionId){
            return manager.read(questionId) != null;
        }

        public int getLastQuestionId(){
            List<Question> list = manager.getAll();
            return list.get(list.size()-1).getQuestionId();
        }

        public Question getQuestionById(int questionId){
            return manager.read(questionId);
        }
    }

}
