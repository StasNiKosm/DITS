package controllers.admin;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import repository.dao.emuns.RoleEnum;
import repository.dao.entities.User;
import services.TestService;
import services.UserSecurityService;
import services.UserService;
import services.user.TestResultResolver;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserManagementAdminController {

    UserService userService;

    PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    /*@PostMapping(value = "/admin/createUser", consumes = "application/json")
    @ResponseBody
    public String addUser(@RequestBody User user){
        this.userService.registerNewUser(user.getLogin(), user.getFirstName(), user.getLastName(), passwordEncoder.encode(user.getPassword()), RoleEnum.valueOf(user.getRole()));
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
//        jsonObject.addProperty("prop", "Неправильно!");
        return jsonObject.toString();
       // model.addAttribute("roles", RoleEnum.getAllRules());
       // modelAndView.setViewName("admin/createUser");
      //  modelAndView.addObject("user", new User());
      //  modelAndView.addObject("roles", RoleEnum.getAllRules());
        //return modelAndView;
    }*/
    @PostMapping(value = "/admin/createUser")
    public ModelAndView addUser(ModelAndView modelAndView, User user) {

        this.userService.registerNewUser(user.getLogin(), user.getFirstName(), user.getLastName(), passwordEncoder.encode(user.getPassword()), RoleEnum.valueOf(user.getRole()));

        modelAndView.setViewName("admin/createUser");
        modelAndView.addObject("successCreation", user.getLogin());
        modelAndView.addObject("user", new User());
        modelAndView.addObject("roles", RoleEnum.getAllRoles());
        return modelAndView;
    }

    @PostMapping(value = "/admin/updateUser")
    public ModelAndView updateUser(ModelAndView modelAndView, User user) {

        user.setPassword(this.userService.getLazyInstance().getUserById(user.getUserId()).getPassword());
        user.setRole(RoleEnum.valueOf(user.getRole()).getName());
        this.userService.getLazyInstance().updateUser(user);

        List<User> users = this.userService.getLazyInstance().getAllUser();
        User userSession = this.userService.getUserFromSession().getUser();
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getUserId() == userSession.getUserId()){
                users.remove(i);
                break;
            }
        }

        modelAndView.setViewName("admin/updateUser");
        modelAndView.addObject("successEdition", user.getFirstName() + " " + user.getLastName() + " login: " + user.getLogin() + " role: " + RoleEnum.byName(user.getRole()));
        modelAndView.addObject("users", users);
        modelAndView.addObject("userJSP", new User());
        modelAndView.addObject("roles", RoleEnum.getAllRoles());
        return modelAndView;
    }

    @PostMapping(value = "/admin/isUniqueLogin")
    @ResponseBody
    public String checkLogin(@RequestParam(value = "login", required = false, defaultValue = "undefined") String login) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("unique", !userService.isLoginRegistered(login));
        return jsonObject.toString();
    }
}
