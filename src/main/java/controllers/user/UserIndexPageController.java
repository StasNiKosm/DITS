package controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import services.UserService;

@Controller
public class UserIndexPageController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ModelAndView userIndexPage(ModelAndView modelAndView) {
        modelAndView.setViewName("user/indexPage");
        modelAndView.addObject("user", userService.getUserFromSession());
        return modelAndView;
    }

}
