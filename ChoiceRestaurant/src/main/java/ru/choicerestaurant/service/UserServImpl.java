package ru.choicerestaurant.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.repository.UserRep;
import ru.choicerestaurant.repository.jdbc.UserRepImplJdbc;
import ru.choicerestaurant.repository.jpa.UserRepImplJpa;
import ru.choicerestaurant.util.exception.NotFoundException;

import javax.annotation.Resource;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

import static ru.choicerestaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserServImpl implements UserServ {
    private static final Logger log = getLogger(UserServImpl.class);


    //@Resource(type = UserRepImplJdbc.class)
    //@Resource(type = UserRepImplJpa.class)
    //@Resource(type = UserRepImplDatajpa.class)
    //@Resource(type = UserRepImplInMem.class)
    @Autowired
    private UserRep userRep;

    /*public UserServImpl(UserRep userRep) {
        this.userRep = userRep;
    }*/ //todo make in constructor

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public User create(User user, Integer userId) {
        log.info("create user {} for user {}", user, userId);
        return checkNotFoundWithId(userRep.save(user), userId);
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public User update(User user, Integer userId) {
        log.info("update user {} for user {}", user, userId);
        return checkNotFoundWithId(userRep.save(user), userId);
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public Boolean delete(Integer id, Integer userId) throws NotFoundException {
        log.info("delete user {} for user {}", id, userId);
        checkNotFoundWithId(userRep.delete(id), userId);
        return true;
    }

    @Override
    public User get(Integer id, Integer userId) throws NotFoundException {
        log.info("get user {} for user {}", id, userId);
        return checkNotFoundWithId(userRep.get(id), id);
    }

    @Override
    @Cacheable("users")
    public List<User> getAll(Integer userId) {
        log.info("get all users for user {}", userId);
        return userRep.getAll();
    }
}