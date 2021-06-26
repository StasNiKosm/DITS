package controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageAdminController {

    @GetMapping(value = "/admin/createTest")
    public ModelAndView createTest(ModelAndView modelAndView){
        modelAndView.setViewName("admin/createTestAdmin");
        return modelAndView;
    }

    @GetMapping(value = "/admin/editUser")
    public ModelAndView editUser(ModelAndView modelAndView){
        modelAndView.setViewName("admin/editUser");
        return modelAndView;
    }

    @GetMapping(value = "/admin/deleteUser")
    public ModelAndView deleteUser(ModelAndView modelAndView){
        modelAndView.setViewName("admin/deleteUser");
        return modelAndView;
    }

    @GetMapping(value = "/admin/createUser")
    public ModelAndView createUser(ModelAndView modelAndView){
        modelAndView.setViewName("admin/createUser");
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
