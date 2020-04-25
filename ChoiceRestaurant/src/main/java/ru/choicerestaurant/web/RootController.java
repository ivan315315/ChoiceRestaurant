package ru.choicerestaurant.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.choicerestaurant.service.RoleServ;
import ru.choicerestaurant.service.UserServ;
import ru.choicerestaurant.util.SecurityUtil;

import javax.servlet.http.HttpServletRequest;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class RootController {
    private static final Logger log = getLogger(RootController.class);

    @Autowired
    private UserServ userServ;

    @Autowired
    private RoleServ roleServ;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        log.info("getAll");
        model.addAttribute("users", userServ.getAll(SecurityUtil.authUserId()));
        return "users";
    }

    @GetMapping("/roles")
    public String getRoles(Model model) {
        log.info("getAll");
        model.addAttribute("roles", roleServ.getAll());
        return "roles";
    }

    /*@PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        return "redirect:meals";
    }*/
}
