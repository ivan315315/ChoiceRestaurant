package ru.choicerestaurant.web.user;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.service.UserServ;
import ru.choicerestaurant.to.UserTo;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

import static ru.choicerestaurant.util.ValidationUtil.checkNew;
import static ru.choicerestaurant.util.ValidationUtil.assureIdConsistent;
import static ru.choicerestaurant.util.SecurityUtil.authUserId;
/*@Controller*/
public class UserAdminController {
    private static final Logger log = getLogger(UserAdminController.class);
    private UserServ userServ;

    private Integer userId = authUserId();

    public UserAdminController(UserServ userServ) {
        this.userServ = userServ;
    }

    //todo all metods check here or in service is object == null

    public User create(UserTo userTo){
        //checkNew(user); todo ficks
        log.info("create user {} for user {}", userTo, userId);
        return userServ.create(userTo);
    }

    public User update(UserTo userTo, Integer id){
        //assureIdConsistent(user, id); todo ficks
        log.info("update user {} for user {}", userTo, userId);
        return userServ.update(userTo);
    }

    public Boolean delete(Integer id){
        log.info("delete user {} for user {}", id, userId);
        return userServ.delete(id);
    }

    public User get(Integer id){
        log.info("get user {} for user {}", id, userId);
        return userServ.get(id);
    }

    public List<User> getAll() {
        log.info("get all users for user {}", userId);
        return userServ.getAll();
    }
}
