package ru.choicerestaurant.to;

import ru.choicerestaurant.model.BaseEntity;

public abstract class BaseTo extends BaseEntity {
    protected Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
