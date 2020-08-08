package ru.choicerestaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "meals")
public class Meal extends BaseEntity {
    @Column
    @NotBlank
    private String name;

    @Column
    @NotNull
    private Double price;

    @Column(name = "lunch_day")
    @NotNull
    private LocalDate lunchDay;

    /*@Column(name = "restaurants_id")*/
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY/*, cascade = CascadeType.ALL*/)
    @JoinColumn(name = "restaurants_id")
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(String name, Double price, LocalDate lunchDay, Restaurant restaurant/*, User user*/) {
        this(null, name, price, lunchDay, restaurant/*, user*/);
    }

    public Meal(Integer id, String name, Double price, LocalDate lunchDay, Restaurant restaurant/*, User user*/) {
        super(id);
        this.name = name;
        this.price = price;
        this.lunchDay = lunchDay;
        this.restaurant = restaurant;
        /*this.user = user;*/
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

    public LocalDate getDatelunchDay() {
        return lunchDay;
    }

    public void setDatelunchDay(LocalDate datelunchDay) {
        this.lunchDay = datelunchDay;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", lunchDay=" + lunchDay +
                ", id=" + id +
                '}';
    }
}
