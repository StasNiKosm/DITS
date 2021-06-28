package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import scriptDB.DBInitializer;
import services.UserService;

import java.sql.SQLException;

@Controller
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ModelAndView userHomePage(ModelAndView modelAndView) throws SQLException {
        modelAndView.setViewName("admin/admin");
        modelAndView.addObject("user", userService.getUserFromSession());
        return modelAndView;
    }

}
