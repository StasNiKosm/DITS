package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.dao.entities.Question;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.LazyManager;

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
        this.lazyInstance = new QuestionServiceFacade(questionEagerManager);
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

    }

}
