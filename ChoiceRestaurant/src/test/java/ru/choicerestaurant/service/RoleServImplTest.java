package ru.choicerestaurant.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.choicerestaurant.ActiveDbProfileResolver;
import ru.choicerestaurant.model.Role;
import ru.choicerestaurant.repository.RoleRep;
import ru.choicerestaurant.repository.TestData;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static ru.choicerestaurant.web.Profiles.DATAJPA;
import static ru.choicerestaurant.web.Profiles.JPA;


@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public class RoleServImplTest extends AbstractServiceTest {

    @Autowired
    private RoleServ service;


    @Test
    public void get() {
        Role role = service.get(TestData.ADMIN.getId());
        assertThat(role).isEqualTo(TestData.ADMIN);
    }

    @Test
    public void getAll() {
        List<Role> roles = service.getAll();
        assertThat(roles).isEqualTo(Arrays.asList(TestData.ADMIN, TestData.USER));
    }
}