package ru.choicerestaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.choicerestaurant.model.Role;

@Transactional(readOnly = true)
public interface CrudRolesRep  extends JpaRepository<Role, Integer> {
}
