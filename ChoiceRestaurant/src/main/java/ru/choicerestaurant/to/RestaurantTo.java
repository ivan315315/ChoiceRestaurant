package ru.choicerestaurant.to;

import ru.choicerestaurant.model.Meal;
import ru.choicerestaurant.model.UserRestaurantDay;

import java.util.Set;

public class RestaurantTo extends BaseTo {
    private String name;
    private Boolean enabled;
    private Set<Meal> meals;
    private Set<UserRestaurantDay> userRestaurantDays;

    //todo
    //private List<Meal> meals;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name, Boolean enabled) {
        super(id);
        this.name = name;
        this.enabled = enabled;
    }

    public RestaurantTo(Integer id, String name, Boolean enabled, Set<Meal> meals, Set<UserRestaurantDay> userRestaurantDays) {
        super(id);
        this.name = name;
        this.enabled = enabled;
        this.meals = meals;
        this.userRestaurantDays = userRestaurantDays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

    public Set<UserRestaurantDay> getUserRestaurantDays() {
        return userRestaurantDays;
    }

    public void setUserRestaurantDays(Set<UserRestaurantDay> userRestaurantDays) {
        this.userRestaurantDays = userRestaurantDays;
    }
}
