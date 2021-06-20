package controllers.debug;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import repository.dao.entities.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import repository.managers.eager.EagerManager;

import java.util.Collections;

@Controller
@RequestMapping("/debug")
public class DebugController {

    private EagerManager<Topic> topicEagerManager;

    @Autowired
    @Qualifier("topicEagerManager")
    public void setTopicEagerManager(EagerManager<Topic> topicEagerManager) {
        this.topicEagerManager = topicEagerManager;
    }

    @GetMapping("/see_all")
    public ModelAndView getAllTestView(ModelAndView model) {
        model.setViewName("debug/information");
        model.addObject("topic_list", topicEagerManager.getAll());
        return model;
    }

    @GetMapping("/topic")
    public ModelAndView getAllTestView(ModelAndView model, @RequestParam(name="id") Integer id) {
        model.setViewName("debug/information");
        model.addObject("topic_list", Collections.singleton(topicEagerManager.read(id)));
        return model;
    }
}
