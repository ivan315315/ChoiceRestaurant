package ru.choicerestaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "users_restaurants_day")
public class UserRestaurantDay extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurants_id")
    @JsonIgnore
    private Restaurant restaurant;

    @Column(name = "lunch_day")
    @NotNull
    private LocalDate lunchDay;

    public UserRestaurantDay() {
    }

    public UserRestaurantDay(User user, Restaurant restaurant, LocalDate lunchDay) {
        this(null, user, restaurant, lunchDay);
    }

    public UserRestaurantDay(Integer id, User user, Restaurant restaurant, LocalDate lunchDay) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.lunchDay = lunchDay;
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

    public LocalDate getlunchDay() {
        return lunchDay;
    }

    public void setlunchDay(LocalDate lunchDay) {
        this.lunchDay = lunchDay;
    }

    @Override
    public String toString() {
        return "UserRestaurantDay{" +
                ", lunchDay=" + lunchDay +
                ", id=" + id +
                '}';
    }
}
