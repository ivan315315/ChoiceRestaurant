package ru.choicerestaurant.service;

import ru.choicerestaurant.model.Role;

import java.util.List;

public interface RoleServ {

    Role get(Integer id);

    List<Role> getAll();
}