package ru.choicerestaurant.repository;

import ru.choicerestaurant.model.Role;
import ru.choicerestaurant.model.User;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;

import static ru.choicerestaurant.model.BaseEntity.START_SEQ;

public class TestData {

    public static final int AUTH_USER_ID = 1;

    public static final int ADMIN_ID = START_SEQ;
    public static final int USER_ID = START_SEQ + 1;

    public static final Role ADMIN = new Role(ADMIN_ID, "Admin");
    public static final Role USER = new Role(USER_ID, "User");

    public static User getNew() {
        return new User("testAdd", "testAdd@gmail.com", "testAdd", LocalDateTime.now(),
                true, ADMIN);
    }

    public static User getUpdated(User created) {
        User updated = created;
        updated.setName("UpdatedName");
        updated.setRole(USER);
        return updated;
    }
}
