package ru.choicerestaurant.model;

import java.time.LocalDate;

public class Meal extends BaseEntity {
    private String name;
    private Double price;
    private LocalDate lunch_day;
    private Restaurant restaurant;
    private User user;

    public Meal(String name, Double price, LocalDate lunch_day, Restaurant restaurant, User user) {
        this(null, name, price, lunch_day, restaurant, user);
    }

    public Meal(Integer id, String name, Double price, LocalDate lunch_day, Restaurant restaurant, User user) {
        super(id);
        this.name = name;
        this.price = price;
        this.lunch_day = lunch_day;
        this.restaurant = restaurant;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDatelunch_day() {
        return lunch_day;
    }

    public void setDatelunch_day(LocalDate datelunch_day) {
        this.lunch_day = datelunch_day;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name=" + name + '\'' +
                ", price='" + price +
                ", lunch_day=" + lunch_day +
                ", restaurant=" + restaurant.getName() +
                ", user=" + user.getName() +
                '}';
    }
}
