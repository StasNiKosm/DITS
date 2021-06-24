package controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageAdminController {

    @GetMapping(value = "/admin/creatTest")
    public ModelAndView createTest(ModelAndView modelAndView){
        modelAndView.setViewName("admin/creatTestAdmin");
        return modelAndView;
    }

    @GetMapping(value = "/admin/creatUser")
    public ModelAndView createUser(ModelAndView modelAndView){
        modelAndView.setViewName("admin/creatUser");
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
