package controllers;

import dao.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    private final PersonService service;

    @Autowired
    public WelcomeController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public String main() {
        return "main";
    }
}
