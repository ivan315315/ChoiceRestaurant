package ru.choicerestaurant.repository.jpa;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.choicerestaurant.model.Meal;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
@Transactional(readOnly = true)
public class MealRepJpa {
    private static final Logger log = getLogger(MealRepJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Meal save(Meal meal) {
        log.info("save {}", meal);
        if (meal.isNew()){
            entityManager.persist(meal);
            return meal;
        } else if (get(meal.getId()) == null) {
            return null;
        }
        return entityManager.merge(meal);
    }

    @Transactional
    public Boolean delete(Integer id) {
        log.info("delete {}", id);
        Meal meal = entityManager.find(Meal.class, id);
        if (meal == null) {
            return false;
        }
        entityManager.remove(meal);
        return true;
    }

    public Meal get(Integer id) {
        log.info("get {}", id);
        return entityManager.find(Meal.class, id);
    }

}
