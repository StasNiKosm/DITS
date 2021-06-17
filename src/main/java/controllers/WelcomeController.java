package controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import repository.dao.entities.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.LazyManager;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
@RequestMapping("/debug")
public class WelcomeController {

    @Autowired
    @Qualifier("topicEagerManager")
    private EagerManager<Topic> topicEagerManager;

    @GetMapping("/see_all")
    public ModelAndView getAllTestView(ModelAndView model) {
        model.setViewName("information");
        model.addObject("topic_list", topicEagerManager.getAll());
        return model;
    }

    @GetMapping("/topic")
    public ModelAndView getAllTestView(ModelAndView model, @RequestParam(name="id") Integer id) {
        model.setViewName("information");
        model.addObject("topic_list", Collections.singleton(topicEagerManager.read(id)));
        return model;
    }

}
