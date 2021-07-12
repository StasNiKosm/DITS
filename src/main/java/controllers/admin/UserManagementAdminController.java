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

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private DeleteUserWithHisStatisticResolver deleteUserWithHisStatisticResolver;

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

    @PostMapping(value = "/admin/addUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String createUser(
            @RequestParam(value = "login", required = false, defaultValue = "undefined") String login,
            @RequestParam(value = "firstName", required = false, defaultValue = "undefined") String firstName,
            @RequestParam(value = "lastName", required = false, defaultValue = "undefined") String lastName,
            @RequestParam(value = "password", required = false, defaultValue = "undefined") String password,
            @RequestParam(value = "role", required = false, defaultValue = "undefined") String role
    ) {
        this.userService.registerNewUser(login, firstName, lastName, this.passwordEncoder.encode(password), RoleEnum.valueOf(role));
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
        return jsonObject.toString();
    }

    @PostMapping(value = "/admin/updateUser")
    public ModelAndView updateUser(ModelAndView modelAndView, int userId, String firstName, String lastName, String login, String role) {
        User user = this.userService.getLazyInstance().getUserById(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setRole(RoleEnum.valueOf(role).getName());
        this.userService.getLazyInstance().updateUser(user);

        modelAndView.setViewName("admin/updateUser");
        modelAndView.addObject("users", this.userService.getAllUserWithoutSessionUser());
        modelAndView.addObject("success", true);
        modelAndView.addObject("roles", RoleEnum.getAllRoles());
        return modelAndView;
    }

    @PostMapping(value = "/admin/isUniqueLogin")
    @ResponseBody
    public String checkLogin(@RequestParam(value = "login", required = false, defaultValue = "undefined") String login) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("unique", !this.userService.isLoginRegistered(login));
        return jsonObject.toString();
    }

    @PostMapping(value = "/admin/deleteUser")
    public ModelAndView deleteUser(ModelAndView modelAndView, int userId) {
        if(userService.getLazyInstance().containsUserById(userId)) {
            User user = this.userService.getLazyInstance().getUserById(userId);
            this.deleteUserWithHisStatisticResolver.deleteUserWithHisStatistic(user);
        }
        modelAndView.setViewName("/admin/deleteUser");
        modelAndView.addObject("users", this.userService.getAllUserWithoutSessionUser());
        modelAndView.addObject("success", true);
        modelAndView.addObject("roles", RoleEnum.getAllRoles());
        return modelAndView;
    }
}
