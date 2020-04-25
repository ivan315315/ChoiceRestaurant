package ru.choicerestaurant.service;

import ru.choicerestaurant.model.User;
import ru.choicerestaurant.util.exception.NotFoundException;

import java.util.List;

public interface UserServ {
    User create(User user, Integer userId);

    User update(User user, Integer userId);

    Boolean delete(Integer id, Integer userId) throws NotFoundException;

    User get(Integer id, Integer userId) throws NotFoundException;

    List<User> getAll(Integer userId);
}
