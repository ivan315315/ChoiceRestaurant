package ru.choicerestaurant.repository.jpa;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.repository.UserRep;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
@Transactional(readOnly = true)
public class UserRepImplJpa implements UserRep {
    private static final Logger log = getLogger(UserRepImplJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public User save(User user) {
        log.info("save user {}", user);
        if (user.isNew()){
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
        return user;
    }

    @Override
    @Transactional
    public Boolean delete(Integer id) {
        log.info("delete user {}", id);
        User user = entityManager.find(User.class, id);
        if (user == null) {
            return false;
        }
        log.debug("delete: " + user);
        entityManager.remove(user);
        return true;
    }

    @Override
    public User get(Integer id) {
        log.info("get user {}", id);
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        log.info("get all users");
        TypedQuery<User> typedQuery = entityManager.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.role", User.class);
        return typedQuery.getResultList();
    }
}
