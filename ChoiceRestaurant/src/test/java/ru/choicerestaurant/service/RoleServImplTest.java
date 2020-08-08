package ru.choicerestaurant.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.choicerestaurant.ActiveDbProfileResolver;
import ru.choicerestaurant.model.Role;
import ru.choicerestaurant.repository.TestData;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


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
        //todo add in list TestData like user
    }
}