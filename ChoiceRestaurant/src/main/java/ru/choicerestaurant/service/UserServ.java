package ru.choicerestaurant.service;

import ru.choicerestaurant.model.User;
import ru.choicerestaurant.util.exception.NotFoundExeption;

import java.util.List;

public interface UserServ {
    User save(User user);

    Boolean delete(Integer id) throws NotFoundExeption;

    User get(Integer id) throws NotFoundExeption;

    List<User> getAll();
}
