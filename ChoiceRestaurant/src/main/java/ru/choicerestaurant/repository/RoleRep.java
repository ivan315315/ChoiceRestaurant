package ru.choicerestaurant.repository;

import ru.choicerestaurant.model.Role;
import ru.choicerestaurant.model.User;

import java.util.List;

public interface RoleRep {

    Role get(Integer id);

    List<Role> getAll();
}
