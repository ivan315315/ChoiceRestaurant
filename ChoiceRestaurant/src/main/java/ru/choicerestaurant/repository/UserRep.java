package ru.choicerestaurant.repository;

import ru.choicerestaurant.model.User;

import java.util.List;

public interface UserRep {
    User save(User user);

    Boolean delete(Integer id);

    User get(Integer id);

    List<User> getAll();
}
