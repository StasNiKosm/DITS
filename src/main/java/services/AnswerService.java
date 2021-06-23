package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.dao.entities.Answer;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.LazyManager;

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
        this.lazyInstance = new AnswerServiceFacade(answerEagerManager);
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

    }

}
