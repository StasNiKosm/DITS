package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import services.UserService;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ModelAndView userHomePage(ModelAndView modelAndView) {
        modelAndView.setViewName("user/user");
        modelAndView.addObject("user", userService.getPrincipal());
        return modelAndView;
    }

}
