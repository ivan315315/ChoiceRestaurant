package ru.choicerestaurant.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column
    private String name;

    @Column
    @NotBlank
    private String email;

    @Column
    @NotBlank
    private String password;

    @Column
    @NotBlank
    private LocalDateTime registered;

    @Column
    @NotBlank
    private Boolean enabled;

    //@Column(name = "role_id")
    @ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/) //??? .ALL - Удалит из справочника роль
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

    public User(String name, String email, String password, LocalDateTime registered, Boolean enabled, Role role) {
        this(null, name, email, password, registered, enabled, role);
    }

    public User(Integer id, String name, String email, String password, LocalDateTime registered, Boolean enabled, Role role) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registered=" + registered +
                ", enabled=" + enabled +
                ", role=" + role +
                '}';
    }
}
