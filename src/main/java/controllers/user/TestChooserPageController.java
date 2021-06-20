package controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import services.TopicService;

@Controller
public class TestChooserPageController {

    private TopicService topicService;

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }
}
