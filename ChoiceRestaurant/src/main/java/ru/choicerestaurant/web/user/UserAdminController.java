package ru.choicerestaurant.web.user;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.service.UserServ;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

import static ru.choicerestaurant.util.ValidationUtil.checkNew;
import static ru.choicerestaurant.util.ValidationUtil.assureIdConsistent;
import static ru.choicerestaurant.util.SecurityUtil.authUserId;
@Controller
public class UserAdminController {
    private static final Logger log = getLogger(UserAdminController.class);
    private UserServ userServ;

    private Integer userId = authUserId();

    public UserAdminController(UserServ userServ) {
        this.userServ = userServ;
    }

    //todo all metods check here or in service is object == null

    public User create(User user){
        checkNew(user);
        log.info("create user {} for user {}", user, userId);
        return userServ.create(user, userId);
    }

    public User update(User user, Integer id){
        assureIdConsistent(user, id);
        log.info("update user {} for user {}", user, userId);
        return userServ.update(user, userId);
    }

    public Boolean delete(Integer id){
        log.info("delete user {} for user {}", id, userId);
        return userServ.delete(id, userId);
    }

    public User get(Integer id){
        log.info("get user {} for user {}", id, userId);
        return userServ.get(id, userId);
    }

    public List<User> getAll() {
        log.info("get all users for user {}", userId);
        return userServ.getAll(userId);
    }
}
