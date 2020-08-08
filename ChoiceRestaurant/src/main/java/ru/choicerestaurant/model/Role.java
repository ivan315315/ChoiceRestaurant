package ru.choicerestaurant.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {
    @Column
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role() {
    }

    public Role(String name) {
        this(null, name);
    }

    public Role(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public Role(Role role){
        this(role.getId(), role.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public String getAuthority() {
        //todo 9_08 10 min
        return name;
    }
}
