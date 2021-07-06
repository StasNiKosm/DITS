package controllers.admin;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
            @RequestParam(name = "name", required = false, defaultValue = "undefined") String name,
            @RequestParam(name = "description", required = false, defaultValue = "undefined") String description
    ) {
        topicService.getLazyInstance().createTopic(name, description);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
//        jsonObject.addProperty("message", "Новый ");
        return jsonObject.toString();

    }

    @PostMapping(value = "/admin/isUniqueTopicName")
    @ResponseBody
    public String checkLogin(@RequestParam(value = "name", required = false, defaultValue = "undefined") String name) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("unique", !topicService.getLazyInstance().isTopicWithName(name));
        return jsonObject.toString();
    }

        @PostMapping(value = "/admin/editTopic", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String editTopic(
            @RequestParam(name = "id", required = false, defaultValue = "undefined") int id,
            @RequestParam(name = "name", required = false, defaultValue = "undefined") String name,
            @RequestParam(name = "description", required = false, defaultValue = "undefined") String description
    ) {
        Topic topic = topicService.getLazyInstance().getTopicById(id);
        topic.setName(name);
        topic.setDescription(description);
        topicService.getLazyInstance().updateTopic(topic);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
//        jsonObject.addProperty("message", "Новый ");
        return jsonObject.toString();

    }

}
