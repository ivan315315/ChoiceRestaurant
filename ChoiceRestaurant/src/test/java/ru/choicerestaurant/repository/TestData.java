package ru.choicerestaurant.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.choicerestaurant.TestMatcher;
import ru.choicerestaurant.model.Restaurant;
import ru.choicerestaurant.model.Role;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.model.UserRestaurantDay;
import ru.choicerestaurant.service.UserRestaurantDayServ;
import ru.choicerestaurant.to.MealTo;
import ru.choicerestaurant.to.RestaurantTo;
import ru.choicerestaurant.to.UserRestaurantDayTo;
import ru.choicerestaurant.to.UserTo;
import ru.choicerestaurant.util.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static ru.choicerestaurant.model.BaseEntity.START_SEQ;

public class TestData {

    public static final int AUTH_USER_ID = 1;
    public static final int ADMIN_ID = START_SEQ;
    public static final int USER_ID = START_SEQ + 1;
    public static TestMatcher<Role> roleMatcher = TestMatcher.usingEquals(Role.class);
    public static final Role ADMIN = new Role(ADMIN_ID, "Admin");
    public static final Role USER = new Role(USER_ID, "User");
    public static final List<Role> roles = new ArrayList<>();
    static {
        Collections.addAll(roles, ADMIN, USER);
    }

    /*@Autowired
    private static UserRestaurantDayServ userRestaurantDayServ;*/

    public static final List<UserTo> userTos = new ArrayList<>();
    static {
        Collections.addAll(userTos,
                new UserTo(100002, "Admin_1", "admin_1@gmail.com", "admin_1", null, null, 100000),
                new UserTo(100003, "User_1", "user_1@yandex.ru", "password_1", null, null, 100001),
                new UserTo(100004, "User_2", "user_2@yandex.ru", "password_2", null, null, 100001),
                new UserTo(100020, "User_3", "user_3@yandex.ru", "password_3", null, null, 100001),
                new UserTo(100021, "User_4", "user_4@yandex.ru", "password_4", null, null, 100001),
                new UserTo(100024, "User_5", "user_5@yandex.ru", "password_5", null, null, 100001)
        );
    }
    //public static TestMatcher<UserTo> userToMatcher = TestMatcher.usingEquals(UserTo.class);
    public static TestMatcher<User> userMatcher = TestMatcher.usingFieldsComparator(User.class, "userRestaurantDays");
    public static UserTo getNewUser() {
        return new UserTo(null,"testAdd", "testAdd@gmail.com", "testAdd", LocalDateTime.now(),
                true, ADMIN.getId());
    }


    public static final List<RestaurantTo> restaurantTos = new ArrayList<>();
    static {
        Collections.addAll(restaurantTos,
                new RestaurantTo(100005, "Fish home", true),
                new RestaurantTo(100006, "Big Italia", true),
                new RestaurantTo(100007, "Ukraine kitchen", true)
        );
    }
    public static TestMatcher<Restaurant> restaurantMatcher = TestMatcher.usingFieldsComparator(Restaurant.class);
    public static RestaurantTo getNewRestaurant() {
        return new RestaurantTo(null, "testAdd", null);
    }


    public static final List<MealTo> mealTos = new ArrayList<>();
    static {
        Collections.addAll(mealTos,
                new MealTo(100008, "Fried trout", 10.5, LocalDate.of(2020, 1, 1), 100005),
                new MealTo(100009, "Boiled shrimp", 11.5, LocalDate.of(2020, 1, 2), 100005),
                new MealTo(100010, "Shark fin soup", 7.5, LocalDate.of(2020, 1, 1), 100005),
                new MealTo(100011, "Borscht", 17.5, LocalDate.of(2020, 1, 2), 100007),
                new MealTo(100012, "The Kievs cutlets", 3.0, LocalDate.of(2020, 1, 2), 100007),
                new MealTo(100013, "Potato pancakes", 5.0, LocalDate.of(2020, 1, 1), 100007),
                new MealTo(100014, "Fried potatoes with mushrooms", 27.3, LocalDate.of(2020, 1, 1), 100007)
        );
    }
    public static MealTo getNewMeal() {
        return new MealTo(null, "testAdd", 33.3, LocalDate.of(2020, 1, 1), 100005);
    }
    /*static {
        mealTos.stream().filter()
        restaurantTos.stream()
                .map(restaurantTo -> restaurantTo.setMeals(mealTos.stream()
                        .filter(mealTo -> mealTo.getRestaurantId() == restaurantTo.getId())))
                .collect(Collectors.toSet());
                //todo add to, and in convertor convert list/set
    }*/


    public static final String dateSt = "2022-01-02";
    public static final LocalDate voteDate = Utils.stringDateToLocalDate(dateSt);//LocalDate.of(2022, 1, 2);
    public static final List<UserRestaurantDayTo> userRestaurantDayTos = new ArrayList<>();
    static {
        Collections.addAll(userRestaurantDayTos,
                new UserRestaurantDayTo(100015, 100003, 100005, LocalDate.of(2022, 1, 1)),
                new UserRestaurantDayTo(100016, 100004, 100005, LocalDate.of(2022, 1, 1)),
                new UserRestaurantDayTo(100017, 100003, 100005, voteDate),
                new UserRestaurantDayTo(100018, 100004, 100007, voteDate),
                new UserRestaurantDayTo(100019, 100002, 100005, voteDate),
                new UserRestaurantDayTo(100022, 100020, 100005, voteDate),
                new UserRestaurantDayTo(100023, 100021, 100007, voteDate)
        );
    }
    public static List<UserRestaurantDayTo> getVoteStatisticTest(LocalDate lunchDay) {
        return userRestaurantDayTos.stream()
                .filter(userRestaurantDayTo -> userRestaurantDayTo.getLunchDay().equals(lunchDay)).collect(Collectors.toList());
    }
    public static UserRestaurantDayTo getNewUserRestaurantDay() {
        return new UserRestaurantDayTo(null, 100002, 100005, LocalDate.of(2021, 1, 1));
    }
    public static UserRestaurantDayTo getOldUserRestaurantDay() {
        return new UserRestaurantDayTo(null, 100003, 100005, LocalDate.of(2017, 1, 1));
        //todo not exist day
    }
    public static UserRestaurantDayTo getVote(LocalDate lunchDay) {
        return new UserRestaurantDayTo(null, 100024, 100005, lunchDay);
    }




    /*public static User getUpdated(User created) {
        User updated = created;
        updated.setName("UpdatedName");
        updated.setRole(USER);
        return updated;
    }*/
    public static User getForAuth() {
        return new User(100002, "Admin_1", "admin_1@gmail.com", "admin_1", LocalDateTime.now(),
                true, ADMIN);
        //todo take from to object, that create up
    }

}
