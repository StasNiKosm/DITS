package controllers;

import dao.entities.Topic;
import dao.intefaces.EagerRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class WelcomeController {

    @Autowired
    private EagerRepositoryService<Topic> topicService;

    @GetMapping(name="/all_test")
    public ModelAndView getAllTestView(ModelAndView model) {
        model.setViewName("information");
        model.addObject("topic_list", topicService.loadAll());
        return model;
    }

}
