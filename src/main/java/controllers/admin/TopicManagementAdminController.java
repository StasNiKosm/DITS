package controllers.admin;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import repository.dao.entities.Topic;
import services.TopicService;

@Controller
public class TopicManagementAdminController {

    private TopicService topicService;

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping(value = "/admin/addTopic", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String createTopic(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description
    ) {
        topicService.getLazyInstance().createTopic(name, description);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
        return jsonObject.toString();

    }

    @PostMapping(value = "/admin/isUniqueTopicName")
    @ResponseBody
    public String checkLogin(@RequestParam(value = "name", required = false, defaultValue = "undefined") String name) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("unique", !topicService.getLazyInstance().isTopicWithName(name));
        return jsonObject.toString();
    }

    @PostMapping(value = "/admin/editTopic")
    public ModelAndView editTopic(ModelAndView modelAndView, int topicId, String topicName, String topicDescription) {
        Topic topic = topicService.getLazyInstance().getTopicById(topicId);
        topic.setName(topicName);
        topic.setDescription(topicDescription);
        topicService.getLazyInstance().updateTopic(topic);
        modelAndView.setViewName("admin/editTopic");
        modelAndView.addObject("success", true);
        modelAndView.addObject("topics", this.topicService.getLazyInstance().getAllTopics());
        return modelAndView;
    }

    @PostMapping(value = "/admin/deleteTopic")
    public ModelAndView deleteTopic(ModelAndView modelAndView, int topicId) {
        if(topicService.getLazyInstance().containsTopicById(topicId)) {
            Topic topic = topicService.getLazyInstance().getTopicById(topicId);
            topicService.getLazyInstance().deleteTopic(topic);
        }
        modelAndView.setViewName("admin/deleteTopic");
        modelAndView.addObject("success", true);
        modelAndView.addObject("topics", this.topicService.getEagerInstance().getAllTopics());
        return modelAndView;
    }
}
