package ru.choicerestaurant.repository.datajpa;

import org.slf4j.Logger;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.repository.UserRep;
import ru.choicerestaurant.repository.jpa.UserRepImplJpa;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
@Repository
public class UserRepImplDatajpa implements UserRep {
    private static final Logger log = getLogger(UserRepImplJpa.class);

    private CrudUserRep crudUserRep;

    public UserRepImplDatajpa(CrudUserRep crudUserRep) {
        this.crudUserRep = crudUserRep;
    }

    @Override
    public User save(User user) {
        log.debug("Save user: " + user.toString());
        return crudUserRep.save(user);
    }

    @Override
    public Boolean delete(Integer id) {
        log.debug("id = " + id);
        return crudUserRep.delete(id) != 0;
    }

    @Override
    public User get(Integer id) {
        log.debug("id = " + id);
        return crudUserRep.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        log.debug("getAll");
        return crudUserRep.findAll();
    }
}
