package ru.choicerestaurant.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import ru.choicerestaurant.ActiveDbProfileResolver;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.to.UserTo;
import ru.choicerestaurant.util.Utils;
import ru.choicerestaurant.util.exception.NotFoundException;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.choicerestaurant.repository.TestData.*;
import static ru.choicerestaurant.DataGeneration.*;

/*@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)*/
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public class UserServImplTest extends AbstractServiceTest {

    @Autowired
    private UserServImpl userServImpl;

    @Autowired
    private CacheManager cacheManager;

    /*@Before
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
    }*/

    @Test
    public void create() {
        UserTo newUserTo = getNewUser();
        User userNew = userServImpl.getObjectByToObject(newUserTo);
        User created = userServImpl.create(newUserTo);
        Integer createdId = created.getId();
        userNew.setId(createdId);
        assertThat(created).isEqualToIgnoringGivenFields(userNew, "registered");
    }

    @Test
    public void updateNotFound() {
        UserTo userTo = getRandomElement(userTos);
        Integer firstId = userTo.getId();
        userTo.setId(getNotExistNum(userServImpl.getAll()));
        assertThrows(NotFoundException.class, () -> userServImpl.update(userTo));
        userTo.setId(firstId);
    }

    @Test ()
    public void delete() {
        Integer id = getRandomElement(userTos).getId();
        userServImpl.delete(id);
        assertThrows(NotFoundException.class, () -> userServImpl.get(id));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> userServImpl.delete(getNotExistNum(userServImpl.getAll())));
    }

    @Test
    public void get() {
        UserTo userTo = getRandomElement(userTos);
        System.out.println("userTos" + userTos);
        User user = userServImpl.getObjectByToObject(userTo);
        assertThat(userServImpl.get(userTo.getId())).isEqualTo(user);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> userServImpl.get(getNotExistNum(userServImpl.getAll())));
    }

    @Test
    public void getAll() {
        System.out.println("userTos" + userTos);
        System.out.println("userServImpl.getAll()" + userServImpl.getAll());
        assertThat(userServImpl.getAll()).isEqualTo(Utils.sortList(userTos.stream()
                .map(userTo -> userServImpl.getObjectByToObject(userTo))
                .collect(Collectors.toList()))
        );
    }
}