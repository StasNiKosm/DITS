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
import services.user.DeleteUserWithHisStatisticResolver;
import services.user.TestResultResolver;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserManagementAdminController {

    UserService userService;

    PasswordEncoder passwordEncoder;

    DeleteUserWithHisStatisticResolver deleteUserWithHisStatisticResolver;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setDeleteUserWithHisStatisticResolver(DeleteUserWithHisStatisticResolver deleteUserWithHisStatisticResolver) {
        this.deleteUserWithHisStatisticResolver = deleteUserWithHisStatisticResolver;
    }

    @PostMapping(value = "/admin/createUser")
    public ModelAndView addUser(ModelAndView modelAndView, User user) {

        this.userService.registerNewUser(user.getLogin(), user.getFirstName(), user.getLastName(), passwordEncoder.encode(user.getPassword()), RoleEnum.valueOf(user.getRole()));

        modelAndView.setViewName("redirect:/admin/createUser");
//        modelAndView.addObject("successCreation", user.getLogin());
//        modelAndView.addObject("user", new User());
//        modelAndView.addObject("roles", RoleEnum.getAllRoles());
        return modelAndView;
    }

    @PostMapping(value = "/admin/addUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
//    public User createUser(@RequestBody User userData) {
    public String createUser(
            @RequestParam(value = "login", required = false, defaultValue = "undefined") String login,
            @RequestParam(value = "firstName", required = false, defaultValue = "undefined") String firstName,
            @RequestParam(value = "lastName", required = false, defaultValue = "undefined") String lastName,
            @RequestParam(value = "password", required = false, defaultValue = "undefined") String password,
            @RequestParam(value = "role", required = false, defaultValue = "undefined") String role ) {
        this.userService.registerNewUser(login, firstName, lastName, passwordEncoder.encode(password), RoleEnum.valueOf(role));

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
        return jsonObject.toString();
    }

    @PostMapping(value = "/admin/updateUser")
    public ModelAndView updateUser(ModelAndView modelAndView, User user) {

        user.setPassword(this.userService.getLazyInstance().getUserById(user.getUserId()).getPassword());
        user.setRole(RoleEnum.valueOf(user.getRole()).getName());
        this.userService.getLazyInstance().updateUser(user);

        List<User> users = this.userService.getAllUserWithoutSessionUser();

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

    @PostMapping(value = "/admin/deleteUser")
    public ModelAndView deleteUser(ModelAndView modelAndView, User user) {

        List<User> users = this.userService.getAllUserWithoutSessionUser();
        for (User value : users) {
            if (value.getUserId() == user.getUserId()) {
                user.setPassword(value.getPassword());
                users.remove(value);
                break;
            }
        }

        deleteUserWithHisStatisticResolver.deleteUserWithHisStatistic(user);


        modelAndView.setViewName("redirect:/admin/deleteUser");
//        modelAndView.addObject("successDeletion", user.getFirstName() + " " + user.getLastName() + " login: " + user.getLogin() + " role: " + RoleEnum.byName(user.getRole()));
//        modelAndView.addObject("userJSP", new User());
//        modelAndView.addObject("users", users);
//        modelAndView.addObject("roles", RoleEnum.getAllRoles());
        return modelAndView;
    }
}
