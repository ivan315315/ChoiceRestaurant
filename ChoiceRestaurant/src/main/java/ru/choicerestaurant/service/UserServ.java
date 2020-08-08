package ru.choicerestaurant.service;

import ru.choicerestaurant.model.User;
import ru.choicerestaurant.to.UserTo;
import ru.choicerestaurant.util.exception.NotFoundException;

import java.util.List;

public interface UserServ {
    User create(UserTo userTo);

    User update(UserTo userTo);

    Boolean delete(Integer id) throws NotFoundException;

    User get(Integer id) throws NotFoundException;

    List<User> getAll();
}
