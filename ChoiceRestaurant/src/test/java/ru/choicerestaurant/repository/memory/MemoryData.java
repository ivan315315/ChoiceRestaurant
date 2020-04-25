package ru.choicerestaurant.repository.memory;

import ru.choicerestaurant.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemoryData {
    public static final List<Role> roles = Arrays.asList(
            new Role(100000, "Admin"),
            new Role(100001, "User")
    );
    public static final List<User> users0 = Arrays.asList(
            new User(100002, "Admin_1", "admin_1@gmail.com", "admin_1", LocalDateTime.of(2019, 2, 7, 0, 0), true, roles.stream().filter(role -> role.getId() == 100000).findFirst().get()),
            new User(100003, "User_1", "user_1@yandex.ru", "password_1", LocalDateTime.of(2019, 2, 7, 0, 0), true, roles.stream().filter(role -> role.getId() == 100001).findFirst().get()),
            new User(100004, "User_2", "user_2@yandex.ru", "password_2", LocalDateTime.of(2019, 2, 7, 0, 0), true, roles.stream().filter(role -> role.getId() == 100001).findFirst().get())
    );
    //https://stackoverflow.com/questions/9320409/unsupportedoperationexception-at-java-util-abstractlist-add/38815474
    public static final List<User> users = new ArrayList<>(users0); // todo тоже колхоз напоминает, переделать
    /*public static final List<Restaurant> restarants = Arrays.asList(
            new Restaurant(100005, "Fish home", users.get(100002)),
            new Restaurant(100006, "Big Italia", users.get(100002)),
            new Restaurant(100007, "Ukraine kitchen", users.get(100002))
    );
    public static final List<Meal> meals = Arrays.asList(
            new Meal(100008, "Fried trout", 10.5, LocalDate.of(2020, 1, 1), restarants.get(100005), users.get(100002)),
            new Meal(100009, "Boiled shrimp", 11.5, LocalDate.of(2020, 1, 2), restarants.get(100005), users.get(100002)),
            new Meal(100010, "Shark fin soup", 7.5, LocalDate.of(2020, 1, 1), restarants.get(100005), users.get(100002)),
            new Meal(100011, "Borscht", 17.5, LocalDate.of(2020, 1, 2), restarants.get(100007), users.get(100002)),
            new Meal(100012, "The Kievs cutlets", 3.0, LocalDate.of(2020, 1, 2), restarants.get(100007), users.get(100002)),
            new Meal(100013, "Potato pancakes", 5.0, LocalDate.of(2020, 1, 1), restarants.get(100007), users.get(100002)),
            new Meal(100014, "Fried potatoes with mushrooms", 27.3, LocalDate.of(2020, 1, 1), restarants.get(100007), users.get(100002))
    );
    public static final List<UserRestaurantDay> UserRestaurantDays = Arrays.asList(
            new UserRestaurantDay(100015, users.get(100003), restarants.get(100005), LocalDate.of(2020, 1, 1)),
            new UserRestaurantDay(100016, users.get(100004), restarants.get(100005), LocalDate.of(2020, 1, 1)),
            new UserRestaurantDay(100017, users.get(100003), restarants.get(100005), LocalDate.of(2020, 1, 2)),
            new UserRestaurantDay(100018, users.get(100004), restarants.get(100007), LocalDate.of(2020, 1, 2))
    );*/
/*

* */
}
