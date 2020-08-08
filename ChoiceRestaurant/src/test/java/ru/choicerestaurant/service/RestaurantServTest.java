package ru.choicerestaurant.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.choicerestaurant.ActiveDbProfileResolver;
import ru.choicerestaurant.model.Restaurant;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.model.UserRestaurantDay;
import ru.choicerestaurant.to.RestaurantTo;
import ru.choicerestaurant.to.UserTo;
import ru.choicerestaurant.util.Utils;
import ru.choicerestaurant.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.choicerestaurant.DataGeneration.*;
import static ru.choicerestaurant.repository.TestData.*;

@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public class RestaurantServTest extends AbstractServiceTest {

    @Autowired
    private RestaurantServ restaurantServ;

    @Autowired
    private UserRestaurantDayServ userRestaurantDayServ;

    @Test
    public void create() {
        RestaurantTo newRestaurantTo = getNewRestaurant();
        Restaurant restaurantNew = restaurantServ.getObjectByToObject(newRestaurantTo);
        Restaurant created = restaurantServ.create(newRestaurantTo);
        Integer createdId = created.getId();
        restaurantNew.setId(createdId);
        assertThat(created).isEqualTo(restaurantNew);
    }

    @Test
    public void update() {
        RestaurantTo restaurantTo = getRandomElement(restaurantTos);
        restaurantTo.setName(getNewString(restaurantTo.getName()));
        restaurantTo.setEnabled(changeBoolean(restaurantTo.getEnabled()));
        Restaurant restaurant = restaurantServ.getObjectByToObject(restaurantTo);
        Restaurant updated = restaurantServ.update(restaurantTo);
        assertThat(updated).isEqualTo(restaurant);
    }

    @Test
    public void updateNotFound() {
        RestaurantTo restaurantTo = getRandomElement(restaurantTos);
        Integer firstId = restaurantTo.getId();
        restaurantTo.setId(getNotExistNum(restaurantServ.getAll()));
        assertThrows(NotFoundException.class, () -> restaurantServ.update(restaurantTo));
        restaurantTo.setId(firstId);
    }

    @Test ()
    public void delete() {
        Integer id = getRandomElement(restaurantTos).getId();
        restaurantServ.delete(id);
        assertThrows(NotFoundException.class, () -> restaurantServ.get(id));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> restaurantServ.delete(getNotExistNum(restaurantServ.getAll())));
    }

    @Test
    public void get() {
        RestaurantTo restaurantTo = getRandomElement(restaurantTos);
        Restaurant restaurant = restaurantServ.getObjectByToObject(restaurantTo);

        /*Restaurant restaurant2 = restaurantServ.get(restaurantTo.getId());
        System.out.println(restaurant);
        System.out.println(restaurant2);
        todo:
         Restaurant{name='Fish home', enabled=true, meals=null, userRestaurantDays=null, id=100005}
         Restaurant{name='Fish home', enabled=true, meals=[Meal{name='Boiled shrimp', price=11.5, lunchDay=2020-01-02, id=100009}, Meal{name='Fried trout', price=10.5, lunchDay=2020-01-01, id=100008}, Meal{name='Shark fin soup', price=7.5, lunchDay=2020-01-01, id=100010}], userRestaurantDays=[UserRestaurantDay{, lunchDay=2020-01-02, id=100017}, UserRestaurantDay{, lunchDay=2020-01-01, id=100016}, UserRestaurantDay{, lunchDay=2020-01-01, id=100015}], id=100005}
        */
        assertThat(restaurantServ.get(restaurantTo.getId())).isEqualTo(restaurant);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> restaurantServ.get(getNotExistNum(restaurantServ.getAll())));
    }

    @Test
    public void getVoteStatistic() {
        Map<Restaurant, Integer> vote = restaurantServ.getVoteStatistic(voteDate);
        Map<Restaurant, Long> voteMapCheck = getVoteStatisticTest(voteDate).stream()
                .map(userRestaurantDayTo -> userRestaurantDayServ.getObjectByToObject(userRestaurantDayTo))
                .collect(Collectors.groupingBy(UserRestaurantDay::getRestaurant, Collectors.counting()));
        Map<Restaurant, Integer> voteCheck = voteMapCheck.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (int)(long)e.getValue()));
        assertThat(vote).isEqualTo(voteCheck);
    }

    @Test
    public void addAndGetVoteStatistic() {
        userRestaurantDayServ.create(getVote(voteDate));
        Map<Restaurant, Integer> vote = restaurantServ.getVoteStatistic(voteDate);
        Map<Restaurant, Long> voteMapCheck = getVoteStatisticTest(voteDate).stream()
                .map(userRestaurantDayTo -> userRestaurantDayServ.getObjectByToObject(userRestaurantDayTo))
                .collect(Collectors.groupingBy(UserRestaurantDay::getRestaurant, Collectors.counting()));
        Map<Restaurant, Integer> voteCheck = voteMapCheck.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (int)(long)e.getValue()));
        Restaurant restaurant = vote.keySet().stream().filter(restaurant1 -> restaurant1.getName().equals("Fish home")).findFirst().orElse(null);
        voteCheck.put(restaurant, voteCheck.get(restaurant) + 1);
        assertThat(vote).isEqualTo(voteCheck);
    }

    /*@Test
    public void getAll() {*/
        //todo
        /*RestaurantTo res = restaurantTos.stream().filter(rest -> rest.getId() == 100005).findFirst().orElse(null);
        Restaurant restaurant1 = restaurantServ.getObjectByToObject(res);
        Restaurant restaurant2 = restaurantServ.get(100005);
        System.out.println(restaurant1);
        System.out.println(restaurant2);*/

        /*System.out.println(restaurantServ.getAll());
        System.out.println(restaurantTos.stream()
                .map(restaurantTo -> restaurantServ.getObjectByToObject(restaurantTo))
                .collect(Collectors.toList()));*/

        /*assertThat(restaurantServ.getAll()).isEqualTo(Utils.sortList(restaurantTos.stream()
                .map(restaurantTo -> restaurantServ.getObjectByToObject(restaurantTo))
                .collect(Collectors.toList()))
        );*/
    /*}*/
}

