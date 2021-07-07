package controllers.admin;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import repository.dao.entities.Test;
import repository.dao.entities.Topic;
import services.TestService;
import services.TopicService;

@Controller
public class TestManagementAdminController {

    private TopicService topicService;

    private TestService testService;

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @PostMapping(value = "/admin/addTest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String addTest(
            //возникает java.lang.NumberFormatException: For input string: "", если topicId - int
            @RequestParam(name = "topicId", required = false) String topicId,
            @RequestParam(name = "topicName", required = false) String topicName,
            @RequestParam(name = "topicDescription", required = false) String topicDescription,
            @RequestParam(name = "testName") String testName,
            @RequestParam(name = "testDescription", required = false) String testDescription
    ){

        System.out.println("___________________________________");
        System.out.println("topicID= " + topicId);
        System.out.println("topicName= " + topicName);
        System.out.println("topicDescription= " + topicDescription);
        System.out.println("testName= " + testName);
        System.out.println("topicDescription= " + testDescription);
        System.out.println("___________________________________");

        Topic topic;
        if(topicId.isEmpty()){
            topic = new Topic();
            topic.setName(topicName);
            topic.setDescription(topicDescription);
        } else {
            topic = topicService.getLazyInstance().getTopicById(Integer.parseInt(topicId));
        }
        topicService.getLazyInstance().createTopic(topic);
        Test test = new Test();
        test.setName(testName);
        test.setDescription(testDescription);
        test.setTopic(topic);
        testService.getLazyInstance().createTest(test);

        System.out.println("TEST ID: " + test.getTestId());

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
        jsonObject.addProperty("newTestId", test.getTestId());
//        jsonObject.addProperty("message", "Новый ");
        return jsonObject.toString();
    }



    @PostMapping(value = "/admin/createAndFillTest")
    public ModelAndView createTest(
            ModelAndView modelAndView,
                                //    Test test
//                                    Topic topic,
            @RequestParam(name = "topicId", required = false) int topicId,
            @RequestParam(name = "topicName", required = false) String topicName,
            @RequestParam(name = "topicDescription", required = false) String topicDescription,
            @RequestParam(name = "testName") String testName,
            @RequestParam(name = "testDescription", required = false) String testDescription
    ) {

        //CREATE TEST
        System.out.println("___________________________________");
        System.out.println("topicID= " + topicId);
        System.out.println("topicName= " + topicName);
        System.out.println("topicDescription= " + topicDescription);
        System.out.println("testName= " + testName);
        System.out.println("topicDescription= " + testDescription);
        System.out.println("___________________________________");

        modelAndView.setViewName("admin/admin");

        return modelAndView;
    }

    @PostMapping(value = "/admin/isUniqueTestName")
    @ResponseBody
    public String checkLogin(@RequestParam(value = "name", required = false, defaultValue = "undefined") String name) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("unique", !testService.getLazyInstance().isTestWithName(name));
        return jsonObject.toString();
    }


    @PostMapping(value = "/admin/filling_out_the_test")
    public ModelAndView fillingTest(ModelAndView modelAndView, String testId)
    {
        modelAndView.setViewName("admin/questionsPages4Test");
        modelAndView.addObject("test", testService.getLazyInstance().getTestById(Integer.parseInt(testId)));

        return modelAndView;
    }
}
