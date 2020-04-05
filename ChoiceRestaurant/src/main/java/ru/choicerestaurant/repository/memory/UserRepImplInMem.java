package ru.choicerestaurant.repository.memory;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.repository.UserRep;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class UserRepImplInMem implements UserRep {
    private static final Logger log = getLogger(UserRepImplInMem.class);

    //@Override
    public User save(User user) {
        return null;
    }

    //@Override
    public Boolean delete(Integer id) {
        return null;
    }

    //@Override
    public User get(Integer id) {
        return null;
    }

    //@Override
    public List<User> getAll() {
        log.info("metod UserRepImpl.getAll");
        log.debug("metod UserRepImpl.getAll");
        return MemoryData.users;
    }
}
