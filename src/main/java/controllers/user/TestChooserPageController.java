package controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import services.TestService;
import services.TopicService;

@Controller
public class TestChooserPageController {

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

    @GetMapping("/user/test-chooser")
    public ModelAndView chooserPage(ModelAndView modelAndView) {
        modelAndView.setViewName("user/testChooserPage");
        modelAndView.addObject("topics", topicService.getLazyInstance().getAllTopics());
        return modelAndView;
    }


    @PostMapping("/user/test-chooser")
    @ResponseBody
    public String getTestByTopic(@RequestParam(value = "topicId", required = false, defaultValue = "undefined") String topicId) {
        System.out.println(topicId);
        return testService.getTestsAsJson(topicService.getLazyInstance().getTestFromTopicById(1)).toString();
    }

}
