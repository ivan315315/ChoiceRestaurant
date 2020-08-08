package ru.choicerestaurant.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.choicerestaurant.ActiveDbProfileResolver;
import ru.choicerestaurant.model.UserRestaurantDay;
import ru.choicerestaurant.to.UserRestaurantDayTo;
import ru.choicerestaurant.util.exception.VoteTooLateException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.choicerestaurant.repository.TestData.*;

@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public class UserRestaurantDayServTest extends AbstractServiceTest {
    @Autowired
    private UserRestaurantDayServ userRestaurantDayServ;

    @Autowired
    private UserServImpl userServImpl;

    @Test
    public void create() {
        UserRestaurantDayTo newUserRestaurantDayTo = getNewUserRestaurantDay();
        UserRestaurantDay userRestaurantDayNew = userRestaurantDayServ.getObjectByToObject(newUserRestaurantDayTo);
        UserRestaurantDay created = userRestaurantDayServ.create(newUserRestaurantDayTo);
        Integer createdId = created.getId();
        userRestaurantDayNew.setId(createdId);
        assertThat(created).isEqualTo(userRestaurantDayNew);
    }

    @Test
    public void errorNewOldDate() {
        UserRestaurantDayTo newUserRestaurantDayTo = getOldUserRestaurantDay();
        assertThrows(VoteTooLateException.class, () -> userRestaurantDayServ.create(newUserRestaurantDayTo));
    }
}
