package services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.connection.HibernateSessionFactory;
import repository.dao.entities.Question;
import repository.dao.entities.Test;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.LazyManager;

import java.util.Set;

@Service
public class TestService {

    private TopicServiceFacade lazyInstance;

    private TopicServiceFacade eagerInstance;

    @Autowired
    public void setLazyInstance(LazyManager<Test> testLazyManager) {
        this.lazyInstance = new TopicServiceFacade(testLazyManager);
    }

    @Autowired
    public void setEagerInstance(EagerManager<Test> testEagerManager) {
        this.eagerInstance = new TopicServiceFacade(testEagerManager);
    }

    public TopicServiceFacade getEagerInstance() {
        return eagerInstance;
    }

    public TopicServiceFacade getLazyInstance() {
        return lazyInstance;
    }

    public static class TopicServiceFacade {

        private final LazyManager<Test> manager;

        private TopicServiceFacade(LazyManager<Test> manager) {
            this.manager = manager;
        }

        public Set<Question> getQuestionsFromTest(int id) {
            try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
                Test test = manager.read(id, session);
                Hibernate.initialize(test.getQuestions());
                return test.getQuestions();
            }
        }

        public Test getTestById(int id) {
            return manager.read(id);
        }

        public Set<Question> getQuestionsFromTest(Test test) {
            return getQuestionsFromTest(test.getTestId());
        }

    }

    public boolean isTestExist(int id) {
        try {
            getLazyInstance().manager.read(id);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public JsonObject getTestsAsJson(Set<Test> tests) {
        JsonObject result = new JsonObject();
        JsonArray testArray = new JsonArray();
        result.add("tests", testArray);
        tests.forEach(test -> {
            JsonObject currentTestObject = new JsonObject();
            currentTestObject.addProperty("id", test.getTestId());
            currentTestObject.addProperty("name", test.getName());
            currentTestObject.addProperty("description", test.getDescription());
            testArray.add(currentTestObject);
        });
        return result;
    };

}
