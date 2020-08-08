package ru.choicerestaurant.to;

import java.time.LocalDateTime;

public class UserTo extends BaseTo {

    private String name;
    private String email;
    private String password;
    private LocalDateTime registered;
    private Boolean enabled;
    private Integer role;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String email, String password, LocalDateTime registered, Boolean enabled, Integer role) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.enabled = enabled;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registered=" + registered +
                ", enabled=" + enabled +
                ", role=" + role +
                ", id=" + id +
                '}';
    }
}
