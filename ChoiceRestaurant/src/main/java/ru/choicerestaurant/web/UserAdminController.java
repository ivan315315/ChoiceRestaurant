package ru.choicerestaurant.web;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.repository.memory.UserRepImplInMem;
import ru.choicerestaurant.service.UserServ;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
@Controller
public class UserAdminController {
    private static final Logger log = getLogger(UserRepImplInMem.class);
    private UserServ userServ;

    public UserAdminController(UserServ userServ) {
        this.userServ = userServ;
    }

    public User save(User user){
        log.debug(user.toString());
        return userServ.save(user);
    }

    public Boolean delete(Integer id){
        log.debug("metod UserAdminController.delete");
        return userServ.delete(id);
    }

    public User get(Integer id){
        log.debug("metod UserAdminController.get");
        return userServ.get(id);
    }

    public List<User> getAll() {
        log.debug("metod UserAdminController.getAll");
        return userServ.getAll();
    }
}
