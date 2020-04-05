package ru.choicerestaurant.repository.jpa;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import ru.choicerestaurant.model.Role;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.repository.RoleRep;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class RoleRepImplJpa implements RoleRep {
    private static final Logger log = getLogger(RoleRepImplJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role get(Integer id) {
        log.debug("id = " + id);
        return entityManager.find(Role.class ,id);
    }

    @Override
    public List<Role> getAll() {
        TypedQuery<Role> typedQuery = entityManager.createQuery("SELECT r FROM Role r", Role.class);
        return typedQuery.getResultList();
    }
}
