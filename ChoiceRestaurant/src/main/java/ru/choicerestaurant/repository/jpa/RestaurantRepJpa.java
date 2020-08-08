package ru.choicerestaurant.repository.jpa;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.choicerestaurant.model.Restaurant;
import ru.choicerestaurant.model.UserRestaurantDay;
import ru.choicerestaurant.util.Utils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
@Transactional(readOnly = true)
//@EnableAspectJAutoProxy(proxyTargetClass=true)
public class RestaurantRepJpa {
    private static final Logger log = getLogger(RestaurantRepJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        log.info("save {}", restaurant);
        if (restaurant.isNew()){
            entityManager.persist(restaurant);
            return restaurant;
        } else if (get(restaurant.getId()) == null) {
            return null;
        }
        return entityManager.merge(restaurant);
    }

    @Transactional
    public Boolean delete(Integer id) {
        log.info("delete {}", id);
        Restaurant restaurant = entityManager.find(Restaurant.class, id);
        if (restaurant == null) {
            return false;
        }
        entityManager.remove(restaurant);
        return true;
    }

    public Restaurant get(Integer id) {
        log.info("get {}", id);
        return entityManager.find(Restaurant.class, id);
    }

    public List<Restaurant> getAll() {
        log.info("get all");
        TypedQuery<Restaurant> typedQuery = entityManager.createQuery("SELECT DISTINCT r FROM Restaurant r " + //???
                "LEFT JOIN FETCH r.userRestaurantDays LEFT JOIN FETCH r.meals", Restaurant.class);
        return Utils.sortList(typedQuery.getResultList());
    }

    public Map<Restaurant, Integer> getVoteStatistic(LocalDate lunchDay) {
        log.info("get vote statistic");
        /*TypedQuery<Restaurant> typedQuery = entityManager.createQuery("SELECT urd, count(urd) FROM UserRestaurantDay urd " +
                "LEFT JOIN FETCH urd.restaurant WHERE urd.lunchDay =: lunchDay group by urd", Restaurant.class).setParameter("lunchDay", lunchDay);*/
        /*Query query = entityManager.createQuery("SELECT urd.restaurant, count(urd.restaurant) FROM UserRestaurantDay urd " +
                "LEFT JOIN FETCH urd.restaurant r " +
                "WHERE urd.lunchDay =: lunchDay group by r").setParameter("lunchDay", lunchDay);
        List<Object[]> resultList = query.getResultList();
        Map<Restaurant, Integer> sortMap = Utils.sortMapRestaurant();
        sortMap.putAll(resultList.stream().collect(Collectors.toMap(obj -> ((UserRestaurantDay)obj[0]).getRestaurant(), obj -> (int)(long)obj[1])));
        return sortMap;*/

        TypedQuery<UserRestaurantDay> typedQuery = entityManager.createQuery("SELECT urd FROM UserRestaurantDay urd " +
                "LEFT JOIN FETCH urd.restaurant LEFT JOIN FETCH urd.user " +
                "WHERE urd.lunchDay =: lunchDay", UserRestaurantDay.class).setParameter("lunchDay", lunchDay);
        List<UserRestaurantDay> userRestaurantDays = typedQuery.getResultList();
        Map<Restaurant, Long> voteMap = userRestaurantDays.stream()
                .collect(Collectors.groupingBy(UserRestaurantDay::getRestaurant, Collectors.counting()));
        return voteMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (int)(long)e.getValue()));
    }

}
