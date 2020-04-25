package ru.choicerestaurant.repository.datajpa;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import ru.choicerestaurant.model.Role;
import ru.choicerestaurant.repository.RoleRep;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class RoleRepImplDatajpa implements RoleRep {
    private static final Logger log = getLogger(RoleRepImplDatajpa.class);

    private CrudRolesRep crudRolesRep;

    public RoleRepImplDatajpa(CrudRolesRep crudRolesRep) {
        this.crudRolesRep = crudRolesRep;
    }

    @Override
    public Role get(Integer id) {
        log.debug("id = " + id);
        return crudRolesRep.findById(id).orElse(null);
    }

    @Override
    public List<Role> getAll() {
        log.debug("getAll");
        return crudRolesRep.findAll();
    }
}
