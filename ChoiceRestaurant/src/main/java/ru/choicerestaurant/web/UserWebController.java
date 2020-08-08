package ru.choicerestaurant.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.choicerestaurant.service.UserServ;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class UserWebController {
    private static final Logger log = getLogger(UserWebController.class);

    @Autowired
    private UserServ userServ;

    @GetMapping("/users")
    public String getUsers(Model model) {
        log.info("getAll");
        model.addAttribute("users", userServ.getAll());
        return "users";
    }
}
