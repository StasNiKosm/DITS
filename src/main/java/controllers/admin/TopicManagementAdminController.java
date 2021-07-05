package controllers.admin;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
        boolean b = topicService.getLazyInstance().isTopicWithName(name);
        jsonObject.addProperty("unique", !b);
        return jsonObject.toString();
    }

    @PostMapping(value = "/admin/editTopic", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String editTopic(
            @RequestParam(name = "name", required = false, defaultValue = "undefined") String name,
            @RequestParam(name = "description", required = false, defaultValue = "undefined") String description
    ) {
//        topicService.getLazyInstance().updateTopic();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
//        jsonObject.addProperty("message", "Новый ");
        return jsonObject.toString();

    }

}
