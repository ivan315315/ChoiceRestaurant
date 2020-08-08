package ru.choicerestaurant.repository.jpa;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.choicerestaurant.model.UserRestaurantDay;
import ru.choicerestaurant.util.exception.ProhibitedOperationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.time.LocalDate;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
@Transactional(readOnly = true)
public class UserRestaurantDayRepJpa {
    private static final Logger log = getLogger(UserRestaurantDayRepJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public UserRestaurantDay save(UserRestaurantDay userRestaurantDay) {
        log.info("save {}", userRestaurantDay);
        if (userRestaurantDay.isNew()){
            entityManager.persist(userRestaurantDay);
            return userRestaurantDay;
        } else {
            throw new ProhibitedOperationException("Update - prohibited operation");
        }
    }

    @Transactional
    public Boolean delete(Integer id) {
        log.info("delete {}", id);
        UserRestaurantDay userRestaurantDay = entityManager.find(UserRestaurantDay.class, id);
        if (userRestaurantDay == null) {
            return false;
        }
        entityManager.remove(userRestaurantDay);
        return true;
    }

    public UserRestaurantDay get(Integer userId, LocalDate lunchDay) {
        TypedQuery<UserRestaurantDay> typedQuery = entityManager.createQuery("SELECT urd FROM UserRestaurantDay urd " +
                "WHERE urd.user.id =:userId AND urd.lunchDay=:lunchDay", UserRestaurantDay.class)
                .setParameter("userId", userId)
                .setParameter("lunchDay", lunchDay);
        return typedQuery.getResultList().stream().findFirst().orElse(null);
    }
}
