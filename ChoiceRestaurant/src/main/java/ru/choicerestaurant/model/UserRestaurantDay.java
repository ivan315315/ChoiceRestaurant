package ru.choicerestaurant.model;

import java.time.LocalDate;

public class UserRestaurantDay extends BaseEntity {
    private User user;
    private Restaurant restaurant;
    private LocalDate lunch_day;

    public UserRestaurantDay(User user, Restaurant restaurant, LocalDate lunch_day) {
        this(null, user, restaurant, lunch_day);
    }

    public UserRestaurantDay(Integer id, User user, Restaurant restaurant, LocalDate lunch_day) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.lunch_day = lunch_day;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getLunch_day() {
        return lunch_day;
    }

    public void setLunch_day(LocalDate lunch_day) {
        this.lunch_day = lunch_day;
    }
}
