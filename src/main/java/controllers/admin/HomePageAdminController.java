package controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import repository.dao.emuns.RoleEnum;
import repository.dao.entities.User;

@Controller
public class HomePageAdminController {

    @GetMapping(value = "/admin/createUser")
    public ModelAndView createUser(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/createUser");
        modelAndView.addObject("successCreation", null);
        modelAndView.addObject("user", new User());
        modelAndView.addObject("roles", RoleEnum.getAllRules());
        return modelAndView;
    }

    @GetMapping(value = "/admin/editUser")
    public ModelAndView editUser(ModelAndView modelAndView){
        modelAndView.setViewName("admin/editUser");
        modelAndView.addObject("roles", RoleEnum.getAllRules());
        return modelAndView;
    }

    @GetMapping(value = "/admin/deleteUser")
    public ModelAndView deleteUser(ModelAndView modelAndView){
        modelAndView.setViewName("admin/deleteUser");
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
        return modelAndView;
    }

    @GetMapping(value = "/admin/deleteTopic")
    public ModelAndView deleteTopic(ModelAndView modelAndView){
        modelAndView.setViewName("admin/deleteTopic");
        return modelAndView;
    }


    @GetMapping(value = "/admin/createTest")
    public ModelAndView createTest(ModelAndView modelAndView){
        modelAndView.setViewName("admin/createTestAdmin");
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
