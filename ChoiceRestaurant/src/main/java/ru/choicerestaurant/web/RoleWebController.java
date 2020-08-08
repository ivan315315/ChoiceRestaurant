package ru.choicerestaurant.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.choicerestaurant.service.RoleServ;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class RoleWebController {
    private static final Logger log = getLogger(RoleWebController.class);

    @Autowired
    private RoleServ roleServ;

    @GetMapping("/roles")
    public String getRoles(Model model) {
        log.info("getAll");
        model.addAttribute("roles", roleServ.getAll());
        return "roles";
    }
}
