package services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import repository.dao.entities.Test;

import java.util.Set;

@Service
public class TestService {

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
