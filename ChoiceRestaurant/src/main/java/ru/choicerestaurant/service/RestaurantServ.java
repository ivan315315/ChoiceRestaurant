package ru.choicerestaurant.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.choicerestaurant.model.Restaurant;
import ru.choicerestaurant.repository.jpa.RestaurantRepJpa;
import ru.choicerestaurant.to.RestaurantTo;
import ru.choicerestaurant.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.choicerestaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServ {
    private static final Logger log = getLogger(RestaurantServ.class);

    private RestaurantRepJpa restaurantRepJpa;

    public RestaurantServ(RestaurantRepJpa restaurantRepJpa) {
        this.restaurantRepJpa = restaurantRepJpa;
    }

    public Restaurant create(RestaurantTo restaurantTo) {
        log.info("create restaurantTo {}", restaurantTo);
        return restaurantRepJpa.save(getObjectByToObject(restaurantTo));
    }

    public Restaurant update(RestaurantTo restaurantTo) {
        log.info("update restaurantTo {}", restaurantTo);
        Restaurant restaurant = getObjectByToObject(restaurantTo);
        return checkNotFoundWithId(restaurantRepJpa.save(restaurant), restaurant.getId());
    }

    public Boolean delete(Integer restaurantId) throws NotFoundException {
        log.info("delete restaurant id {}", restaurantId);
        checkNotFoundWithId(restaurantRepJpa.delete(restaurantId), restaurantId);
        //todo may be not, if voting day has already passed, or if someone has already voted
        return true;
    }

    public Restaurant get(Integer restaurantId) throws NotFoundException {
        log.info("get mealId {}", restaurantId);
        return checkNotFoundWithId(restaurantRepJpa.get(restaurantId), restaurantId);
    }

    public List<Restaurant> getAll() {
        log.info("get all");
        return restaurantRepJpa.getAll();
    }

    public Map<Restaurant, Integer> getVoteStatistic(LocalDate lunchDay) {
        log.info("get vote for restaurant id {} by {}", lunchDay);
        return restaurantRepJpa.getVoteStatistic(lunchDay);
    }

    public Restaurant getObjectByToObject(RestaurantTo restaurantTo) {
        Assert.notNull(restaurantTo, "mealTo must not be null");
        Boolean enabled = restaurantTo.getEnabled();
        if (enabled == null){
            enabled = true;
        }
        return new Restaurant(restaurantTo.getId(), restaurantTo.getName(), enabled);
    }
}
