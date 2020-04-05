package ru.choicerestaurant.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.repository.UserRep;
import ru.choicerestaurant.repository.datajpa.UserRepImplDatajpa;
import ru.choicerestaurant.repository.jpa.UserRepImplJpa;
import ru.choicerestaurant.repository.memory.UserRepImplInMem;
import ru.choicerestaurant.repository.jdbc.UserRepImplJdbc;
import ru.choicerestaurant.util.exception.NotFoundExeption;

import javax.annotation.Resource;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class UserServImpl implements UserServ {
    private static final Logger log = getLogger(UserRepImplInMem.class);


    //@Resource(type = UserRepImplJdbc.class)
    //@Resource(type = UserRepImplJpa.class)
    @Autowired
    @Resource(type = UserRepImplDatajpa.class)
    private UserRep userRep;

    /*public UserServImpl(UserRep userRep) {
        this.userRep = userRep;
    }*/

    //@Override
    public User save(User user) {
        log.debug(user.toString());
        return userRep.save(user);
    }

    //@Override
    public Boolean delete(Integer id) throws NotFoundExeption {
        log.debug("id = " + id);
        return userRep.delete(id);
    }

    //@Override
    public User get(Integer id) throws NotFoundExeption {
        log.debug("metod UserServImpl.get");
        return userRep.get(id);
    }

    //@Override
    public List<User> getAll() {
        log.debug("metod UserServImpl.getAll");
        return userRep.getAll();
    }
}
