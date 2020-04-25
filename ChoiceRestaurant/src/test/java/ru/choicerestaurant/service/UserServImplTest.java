package ru.choicerestaurant.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.choicerestaurant.ActiveDbProfileResolver;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.util.exception.NotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.choicerestaurant.repository.TestData.*;
import static ru.choicerestaurant.DataGeneration.*;
import static ru.choicerestaurant.web.Profiles.DATAJPA;
import static ru.choicerestaurant.web.Profiles.JPA;

@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public class UserServImplTest extends AbstractServiceTest {

    @Autowired
    private UserServ userServ;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
    }

    @Test
    public void create() {
        User newUser = getNew();
        User created = userServ.create(newUser, AUTH_USER_ID);
        Integer createdId = created.getId();
        newUser.setId(createdId);
        assertThat(created).isEqualToIgnoringGivenFields(newUser, "registered");
        assertThat(userServ.get(createdId, AUTH_USER_ID)).isEqualToIgnoringGivenFields(newUser, "registered");
    }

    @Test
    public void update() {
        User newUser = getNew();
        User created = userServ.create(newUser, AUTH_USER_ID);
        Integer userId = created.getId();
        User newUpdate = getUpdated(created);
        User updated = userServ.update(newUpdate, AUTH_USER_ID);
        assertThat(userServ.get(userId, AUTH_USER_ID)).isEqualToIgnoringGivenFields(newUpdate, "registered");

    }

    @Test (expected = NotFoundException.class)
    public void delete() {
        User newUser = getNew();
        User created = userServ.create(newUser, AUTH_USER_ID);
        newUser.setId(created.getId());
        assertThat(userServ.get(created.getId(), AUTH_USER_ID)).isEqualToIgnoringGivenFields(newUser, "registered");
        userServ.delete(created.getId(), AUTH_USER_ID);
        userServ.get(created.getId(), AUTH_USER_ID);
    }

    @Test (expected = NotFoundException.class)
    public void deleteNotFound() {
        userServ.delete(getNotExistNum(userServ.getAll(AUTH_USER_ID)), AUTH_USER_ID);
    }

    @Test
    public void get() {
        User newUser = getNew();
        User created = userServ.create(newUser, AUTH_USER_ID);
        newUser.setId(created.getId());
        assertThat(userServ.get(created.getId(), AUTH_USER_ID)).isEqualToIgnoringGivenFields(newUser, "registered");
    }

    @Test (expected = NotFoundException.class)
    public void getNotFound() {
        userServ.get(getNotExistNum(userServ.getAll(AUTH_USER_ID)), AUTH_USER_ID);
    }

    //todo
    /*@Test
    public void getAll() { }*/
}