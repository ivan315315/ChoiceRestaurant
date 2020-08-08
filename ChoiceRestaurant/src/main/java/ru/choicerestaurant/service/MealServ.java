package ru.choicerestaurant.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.choicerestaurant.model.Meal;
import ru.choicerestaurant.model.Restaurant;
import ru.choicerestaurant.repository.jpa.MealRepJpa;
import ru.choicerestaurant.to.MealTo;
import ru.choicerestaurant.util.exception.NotFoundException;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.choicerestaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServ {
    private static final Logger log = getLogger(MealServ.class);

    private MealRepJpa mealRepJpa;
    private RestaurantServ restaurantServ;

    public MealServ(MealRepJpa mealRepJpa, RestaurantServ restaurantServ) {
        this.mealRepJpa = mealRepJpa;
        this.restaurantServ = restaurantServ;
    }

    public Meal create(MealTo mealTo) {
        log.info("create mealTo {}, restaurantId {}", mealTo, mealTo.getRestaurantId());
        //todo check restaurant if it,s active
        return mealRepJpa.save(getObjectByToObject(mealTo));
    }

    public Meal update(MealTo mealTo) {
        log.info("update mealTo {}, restaurantId {}", mealTo, mealTo.getRestaurantId());
        Meal meal = getObjectByToObject(mealTo);
        return checkNotFoundWithId(mealRepJpa.save(meal), meal.getId());
    }

    public Boolean delete(Integer mealId) throws NotFoundException {
        log.info("delete meal id {}", mealId);
        checkNotFoundWithId(mealRepJpa.delete(mealId), mealId);
        //todo may be not, if voting day has already passed, or if someone has already voted
        return true;
    }

    public Meal get(Integer mealId) throws NotFoundException {
        log.info("get mealId {}", mealId);
        return checkNotFoundWithId(mealRepJpa.get(mealId), mealId);
    }

    public Meal getObjectByToObject(MealTo mealTo) {
        Assert.notNull(mealTo, "mealTo must not be null");
        Restaurant restaurant = restaurantServ.get(mealTo.getRestaurantId());
        return new Meal(mealTo.getId(), mealTo.getName(), mealTo.getPrice(), mealTo.getLunchDay(), restaurant);
    }
}
