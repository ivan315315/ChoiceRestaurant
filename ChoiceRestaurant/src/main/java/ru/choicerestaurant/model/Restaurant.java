package ru.choicerestaurant.model;

public class Restaurant extends BaseEntity {
    private String name;
    private User user;

    public Restaurant(String name, User user) {
        this(null, name, user);
    }

    public Restaurant(Integer id, String name, User user) {
        super(id);
        this.name = name;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
