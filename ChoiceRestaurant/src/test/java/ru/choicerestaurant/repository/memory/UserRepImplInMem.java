package ru.choicerestaurant.repository.memory;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import ru.choicerestaurant.model.BaseEntity;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.repository.UserRep;
import ru.choicerestaurant.util.exception.ErrorStructureDataException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class UserRepImplInMem implements UserRep {
    private static final Logger log = getLogger(UserRepImplInMem.class);

    @Override
    public User save(User user) {
        log.info(user.toString());
        if (user.isNew()) { //todo не потокобезопасно // стрим должен список ид получить, а не юзера и потом макс брать
            user.setId(MemoryData.users.stream().max(Comparator.comparing(BaseEntity::getId)).get().getId() + 1);
        } else {
            MemoryData.users.remove(this.get(user.getId()));
            //todo проверить, что есть или переписать метод
        }
        MemoryData.users.add(user);
        return user;
    }

    @Override
    public Boolean delete(Integer id) {
        log.info("id = " + id);
        return MemoryData.users.remove(this.get(id));
    }

    @Override
    public User get(Integer id) {
        log.info("id = " + id);
        List<User> users = MemoryData.users.stream().filter(user -> user.getId().equals(id)).collect(Collectors.toList());
        if (users.size() > 1) {
            throw new ErrorStructureDataException("id = " + id);
        }
        return users.stream().findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        log.info("");
        return MemoryData.users;
    }
}
