package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import repository.dao.emuns.RoleEnum;
import repository.dao.entities.User;
import services.UserSecurityService;
import services.UserService;

import java.util.HashMap;
import java.util.List;

@Controller
public class HomePageAdminController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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

        List<User> users = this.userService.getAllUserWithoutSessionUser();

        modelAndView.setViewName("admin/updateUser");
        modelAndView.addObject("successEdition", null);
        modelAndView.addObject("users", users);
        modelAndView.addObject("userJSP", new User());
        modelAndView.addObject("roles", RoleEnum.getAllRoles());
        return modelAndView;
    }

    @GetMapping(value = "/admin/deleteUser")
    public ModelAndView deleteUser(ModelAndView modelAndView){

        List<User> users = this.userService.getAllUserWithoutSessionUser();

        modelAndView.setViewName("admin/deleteUser");
        modelAndView.addObject("successDeletion", null);
        modelAndView.addObject("userJSP", new User());
        modelAndView.addObject("users", users);
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
