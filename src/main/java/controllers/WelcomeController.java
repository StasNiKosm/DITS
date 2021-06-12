package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    public WelcomeController() {

    }

    @GetMapping("/hello")
    public String main() {
        System.out.println("touch WelcomeController!");
        return "main";
    }
}
