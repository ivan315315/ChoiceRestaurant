package ru.choicerestaurant.to;

import java.time.LocalDate;

public class UserRestaurantDayTo extends BaseTo {

    private Integer user;
    private Integer restaurant;
    private LocalDate lunchDay;

    public UserRestaurantDayTo() {
    }

    public UserRestaurantDayTo(Integer id, Integer user, Integer restaurant, LocalDate lunchDay) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.lunchDay = lunchDay;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Integer restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getLunchDay() {
        return lunchDay;
    }

    public void setLunchDay(LocalDate lunchDay) {
        this.lunchDay = lunchDay;
    }

    @Override
    public String toString() {
        return "UserRestaurantDayTo{" +
                "user=" + user +
                ", restaurant=" + restaurant +
                ", lunchDay=" + lunchDay +
                ", id=" + id +
                '}';
    }
}
