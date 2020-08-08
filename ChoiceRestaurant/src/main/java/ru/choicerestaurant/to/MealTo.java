package ru.choicerestaurant.to;

import java.time.LocalDate;

public class MealTo extends BaseTo {

    private String name;
    private Double price;
    private LocalDate lunchDay;
    private Integer restaurantId;

    public MealTo() {
    }

    public MealTo(Integer id, String name, Double price, LocalDate lunchDay, Integer restaurantId) {
        super(id);
        this.name = name;
        this.price = price;
        this.lunchDay = lunchDay;
        this.restaurantId = restaurantId;
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

    public LocalDate getLunchDay() {
        return lunchDay;
    }

    public void setLunchDay(LocalDate lunchDay) {
        this.lunchDay = lunchDay;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", lunchDay=" + lunchDay +
                ", restaurantId=" + restaurantId +
                ", id=" + id +
                '}';
    }
}
