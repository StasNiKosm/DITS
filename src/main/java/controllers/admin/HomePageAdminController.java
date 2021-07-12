package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import repository.dao.emuns.RoleEnum;
import repository.dao.entities.Test;
import repository.dao.entities.Topic;
import repository.dao.entities.User;
import services.TestService;
import services.TopicService;
import services.UserSecurityService;
import services.UserService;

import java.util.HashMap;
import java.util.List;

@Controller
public class HomePageAdminController {

    private UserService userService;

    private TopicService topicService;

    private TestService testService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/admin/createUser")
    public ModelAndView createUser(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/createUser");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("roles", RoleEnum.getAllRoles());
        return modelAndView;
    }

    @GetMapping(value = "/admin/updateUser")
    public ModelAndView editUser(ModelAndView modelAndView){
        modelAndView.setViewName("admin/updateUser");
        modelAndView.addObject("success", null);
        modelAndView.addObject("users", this.userService.getAllUserWithoutSessionUser());
        modelAndView.addObject("roles", RoleEnum.getAllRoles());
        return modelAndView;
    }

    @GetMapping(value = "/admin/deleteUser")
    public ModelAndView deleteUser(ModelAndView modelAndView){
        modelAndView.setViewName("admin/deleteUser");
        modelAndView.addObject("success", null);
        modelAndView.addObject("users", this.userService.getAllUserWithoutSessionUser());
        modelAndView.addObject("roles", RoleEnum.getAllRoles());
        return modelAndView;
    }

    @GetMapping(value = "/admin/createTopic")
    public ModelAndView createTopic(ModelAndView modelAndView){
        modelAndView.setViewName("admin/createTopic");
        return modelAndView;
    }

    @GetMapping(value = "/admin/editTopic")
    public ModelAndView editTopic(ModelAndView modelAndView){
        modelAndView.setViewName("admin/editTopic");
        modelAndView.addObject("topics", this.topicService.getLazyInstance().getAllTopics());
        modelAndView.addObject("success", null);
        return modelAndView;
    }

    @GetMapping(value = "/admin/deleteTopic")
    public ModelAndView deleteTopic(ModelAndView modelAndView){
        modelAndView.setViewName("admin/deleteTopic");
        modelAndView.addObject("topics", this.topicService.getEagerInstance().getAllTopics());
        modelAndView.addObject("success", null);
        return modelAndView;
    }


    @GetMapping(value = "/admin/createTest")
    public ModelAndView createTest(ModelAndView modelAndView){
        modelAndView.setViewName("admin/createTestAdmin");
        modelAndView.addObject("topics", this.topicService.getLazyInstance().getAllTopics());
        return modelAndView;
    }

    @GetMapping(value = "/admin/choose_test_for_edition")
    public ModelAndView chooseTest(ModelAndView modelAndView){
        modelAndView.setViewName("admin/chooseTest4Editing");
        modelAndView.addObject("tests", this.testService.getEagerInstance().getAllTests());
        return modelAndView;
    }

    @GetMapping(value = "/admin/choose_test_for_removing")
    public ModelAndView chooseForRemoving(ModelAndView modelAndView){
        modelAndView.setViewName("admin/chooseTest4Removing");
        modelAndView.addObject("tests", this.testService.getLazyInstance().getAllTests());
        modelAndView.addObject("success", null);
        return modelAndView;
    }

    @GetMapping(value = "/admin/statistic")
    public ModelAndView showStatisticPage(ModelAndView modelAndView){
        modelAndView.setViewName("admin/adminStatistic");
        return modelAndView;
    }

    @GetMapping(value = "/goHomeAdmin")
    public ModelAndView goHome(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/admin");
        return modelAndView;
    }
}
