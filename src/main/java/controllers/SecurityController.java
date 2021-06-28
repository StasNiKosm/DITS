package controllers;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import repository.dao.emuns.RoleEnum;
import scriptDB.DBInitializer;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SecurityController {

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

    volatile boolean inited = false;

    @GetMapping("/login")
    public String login() {
        //if (!inited) {
        if (false) {
            DBInitializer.createNewTest();
            inited = true;
        }
        return "login";
    }

    @PostMapping("/accessDenied")
    public String accessDeniedPost() {
        return "accessDenied";
    }

    @GetMapping("/accessDenied")
    public String accessDeniedGet() {
        return "accessDenied";
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        return new RedirectView("/login?logout");
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registerNewUser(@RequestParam("login") String login,
                                  @RequestParam("firstName") String firstName,
                                  @RequestParam("secondName") String secondName,
                                  @RequestParam("password") String password
    ) {
        userService.registerNewUser(login, firstName, secondName, passwordEncoder.encode(password), RoleEnum.User);
        return "/login";
    }

    @PostMapping(value = "/checkLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String checkLogin(@RequestParam(value = "login", required = false, defaultValue = "undefined") String login) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("unique", !userService.isLoginRegistered(login));
        jsonObject.addProperty("prop", "Неправильно!");
        return jsonObject.toString();
    }

}
