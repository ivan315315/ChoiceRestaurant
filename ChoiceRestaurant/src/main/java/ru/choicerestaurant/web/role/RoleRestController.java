package ru.choicerestaurant.web.role;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.choicerestaurant.model.Role;
import ru.choicerestaurant.service.RoleServ;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = RoleRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleRestController {
    private static final Logger log = getLogger(RoleRestController.class);

    static final String REST_URL = "/rest/roles";

    @Autowired
    private RoleServ roleServ;

    @GetMapping
    public List<Role> getRoles() {
        log.info("getAll");
        return roleServ.getAll();
    }

    @GetMapping("/{id}")
    public Role get(@PathVariable int id) {
        log.info("get {}", id);
        return roleServ.get(id);
    }
}
