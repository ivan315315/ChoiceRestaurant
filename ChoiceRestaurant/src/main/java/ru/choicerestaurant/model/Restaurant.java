package ru.choicerestaurant.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant extends BaseEntity {
    @Column
    @NotBlank
    private String name;

    @Column
    @NotNull
    private Boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Meal> meals;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    private Set<UserRestaurantDay> userRestaurantDays;

    public Restaurant() {
    }

    public Restaurant(String name, Boolean enabled) {
        this(null, name, enabled);
    }

    public Restaurant(Integer id, String name, Boolean enabled) {
        super(id);
        this.name = name;
        this.enabled = enabled;
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

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", enabled=" + enabled +
                ", meals=" + meals +
                ", userRestaurantDays=" + userRestaurantDays +
                ", id=" + id +
                '}';
    }
}
