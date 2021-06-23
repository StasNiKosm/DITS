package services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import repository.dao.entities.Test;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.LazyManager;

import java.util.Set;

@Service
public class TestService {

    private TopicServiceFacade lazyInstance;

    private TopicServiceFacade eagerInstance;

    public void setLazyInstance(LazyManager<Test> testLazyManager) {
        this.lazyInstance = new TopicServiceFacade(testLazyManager);
    }

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

    }

    public JsonObject getTestsAsJson(Set<Test> tests) {
        JsonObject result = new JsonObject();
        JsonArray testArray = new JsonArray();
        result.add("tests", testArray);
        tests.forEach(test -> {
            JsonObject currentTestObject = new JsonObject();
            currentTestObject.addProperty("id", test.getTestId());
            currentTestObject.addProperty("name", test.getName());
            testArray.add(currentTestObject);
        });
        return result;
    };

}
