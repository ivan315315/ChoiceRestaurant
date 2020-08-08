package ru.choicerestaurant.web.role;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.choicerestaurant.model.Role;
import ru.choicerestaurant.service.RoleServ;
import ru.choicerestaurant.util.SecurityUtil;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class RoleController {
    private static final Logger log = getLogger(RoleController.class);

    @Autowired
    private RoleServ roleServ;

    public Role get(Integer id) {
        Integer userId = SecurityUtil.authUserId();
        log.info("get role {} for user {}", id, userId);
        return roleServ.get(id);
    }

    public List<Role> getAll() {
        Integer userId = SecurityUtil.authUserId();
        log.info("get all roles for user {}", userId);
        return roleServ.getAll();
    }
}
