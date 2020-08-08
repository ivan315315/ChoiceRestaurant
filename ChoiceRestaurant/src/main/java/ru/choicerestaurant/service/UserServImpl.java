package ru.choicerestaurant.service;

import org.slf4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.choicerestaurant.AuthorizedUser;
import ru.choicerestaurant.model.Role;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.repository.jpa.RoleRepImplJpa;
import ru.choicerestaurant.repository.jpa.UserRepImplJpa;
import ru.choicerestaurant.to.UserTo;
import ru.choicerestaurant.util.TimeUtil;
import ru.choicerestaurant.util.Utils;
import ru.choicerestaurant.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

import static ru.choicerestaurant.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserServImpl implements UserServ, UserDetailsService {
    private static final Logger log = getLogger(UserServImpl.class);

    private UserRepImplJpa userRepImplJpa;
    private RoleRepImplJpa roleRepImplJpa;

    public UserServImpl(UserRepImplJpa userRepImplJpa, RoleRepImplJpa roleRepImplJpa) {
        this.userRepImplJpa = userRepImplJpa;
        this.roleRepImplJpa = roleRepImplJpa;
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public User create(UserTo userTo) {
        log.info("create user {}", userTo);
        return userRepImplJpa.save(getObjectByToObject(userTo));
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public User update(UserTo userTo) {
        log.info("update user {}", userTo);
        return checkNotFoundWithId(userRepImplJpa.save(getObjectByToObject(userTo)), userTo.getId());
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public Boolean delete(Integer id) throws NotFoundException {
        log.info("delete user {}", id);
        checkNotFoundWithId(userRepImplJpa.delete(id), id);
        return true;
    }

    @Override
    public User get(Integer id) throws NotFoundException {
        log.info("get user {}", id);
        return checkNotFoundWithId(userRepImplJpa.get(id), id);
    }

    @Override
    @Cacheable("users")
    public List<User> getAll() {
        log.info("get all");
        return Utils.sortList(userRepImplJpa.getAll());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User authUser = userRepImplJpa.getAll().stream()
                .filter(user -> user.getEmail().equals(email.toLowerCase()))
                .findFirst()
                .orElse(null);
        if (authUser == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(authUser);
    }

    public User getObjectByToObject(UserTo userTo) {
        Assert.notNull(userTo, "userTo must not be null");
        LocalDateTime registered = userTo.getRegistered();
        if (registered == null){
            if (userTo.getId() == null) {
                registered = TimeUtil.getToday();
            } else {
                registered = get(userTo.getId()).getRegistered();
            }
        }
        Boolean enabled = userTo.getEnabled();
        if (enabled == null){
            enabled = true;
        }
        Role role = roleRepImplJpa.get(userTo.getRole());
        return new User(userTo.getId(), userTo.getName(), userTo.getEmail(), userTo.getPassword(),
                registered, enabled, role);
    }
}