package ru.choicerestaurant.model;

//import jdk.nashorn.internal.objects.annotations.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    //@Getter ???
    @Column
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "role"/*, fetch = FetchType.LAZY*/) // ?? для users или для ролей в users // mappedBy = "role" - таблица, где мении, а если их несколько???
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
}
