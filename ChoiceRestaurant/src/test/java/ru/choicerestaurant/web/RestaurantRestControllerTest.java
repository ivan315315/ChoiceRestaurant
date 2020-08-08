package ru.choicerestaurant.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.choicerestaurant.DataGeneration;
import ru.choicerestaurant.model.Restaurant;
import ru.choicerestaurant.model.UserRestaurantDay;
import ru.choicerestaurant.repository.TestData;
import ru.choicerestaurant.service.RestaurantServ;
import ru.choicerestaurant.service.UserRestaurantDayServ;
import ru.choicerestaurant.to.RestaurantTo;
import ru.choicerestaurant.util.exception.NotFoundException;
import ru.choicerestaurant.web.json.JsonUtil;

import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.choicerestaurant.DataGeneration.*;
import static ru.choicerestaurant.TestUtil.readFromJson;
import static ru.choicerestaurant.TestUtil.userHttpBasic;
import static ru.choicerestaurant.repository.TestData.*;

public class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.REST_URL + '/';

    @Autowired
    private RestaurantServ restaurantServ;

    @Autowired
    private UserRestaurantDayServ userRestaurantDayServ;

    @Test
    void create() throws Exception {
        RestaurantTo newRestaurantTo = getNewRestaurant();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .with(userHttpBasic(TestData.getForAuth()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurantTo)))
                .andExpect(status().isCreated());
        Restaurant created = readFromJson(action, Restaurant.class);
        Assertions.assertThat(created).isEqualToIgnoringGivenFields(restaurantServ.get(created.getId()), "registered");
    }

    @Test
    void update() throws Exception {
        RestaurantTo restaurantTo = getRandomElement(restaurantTos);
        RestaurantTo restaurantToUpdate = new RestaurantTo(restaurantTo.getId(), getNewString(restaurantTo.getName()), changeBoolean(restaurantTo.getEnabled()));
        Restaurant restaurant = restaurantServ.getObjectByToObject(restaurantToUpdate);
        ResultActions actionUpdate = perform(MockMvcRequestBuilders.put(REST_URL + restaurant.getId())
                .with(userHttpBasic(TestData.getForAuth()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(restaurantToUpdate)))
                .andExpect(status().isNoContent());
        System.out.println(restaurant.getId());
        System.out.println(restaurantServ.get(restaurant.getId()).getId());
        Assertions.assertThat(restaurant).isEqualTo(restaurantServ.get(restaurant.getId()));
    }

   @Test
    void delete() throws Exception {
        Integer id = getRandomElement(restaurantTos).getId();
        perform(MockMvcRequestBuilders.delete(REST_URL + id)
                .with(userHttpBasic(TestData.getForAuth())))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> restaurantServ.get(id));
    }

   @Test
    void deleteNotFound() throws Exception {
        //todo check json exception
       mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + DataGeneration.getNotExistNum(restaurantServ.getAll()))
               .with(userHttpBasic(TestData.getForAuth())))
               .andExpect(status().isUnprocessableEntity())
               .andDo(print());
    }

    @Test
    void get() throws Exception {
        RestaurantTo restaurantTo = getRandomElement(restaurantTos);
        Restaurant restaurant = restaurantServ.getObjectByToObject(restaurantTo);
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + restaurant.getId())
                .with(userHttpBasic(TestData.getForAuth())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(JsonUtil.writeValue(restaurant)));
    }

    @Test
    void getNotFound() throws Exception {
    // todo with json exception
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + DataGeneration.getNotExistNum(restaurantServ.getAll()))
                .with(userHttpBasic(TestData.getForAuth())))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    /*@Test
    void getAll() throws Exception {
        //System.out.println("TestData.restaurantTos.stream()" + TestData.restaurantTos.stream().map(restaurantTo -> restaurantServ.getObjectByToObject(restaurantTo)).collect(Collectors.toList()));
        //todo in testdata no link set objects,
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(TestData.getForAuth())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestData.restaurantMatcher.contentJson(TestData.restaurantTos.stream()
                        .map(restaurantTo -> restaurantServ.getObjectByToObject(restaurantTo))
                        .collect(Collectors.toList()))
                );
    }*/

   @Test
    void getVoteStatistic() throws Exception {
       Map<Restaurant, Long> voteMapCheck = getVoteStatisticTest(voteDate).stream()
               .map(userRestaurantDayTo -> userRestaurantDayServ.getObjectByToObject(userRestaurantDayTo))
               .collect(Collectors.groupingBy(UserRestaurantDay::getRestaurant, Collectors.counting()));
       Map<Restaurant, Integer> voteCheck = voteMapCheck.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (int)(long)e.getValue()));
        String dateSort = dateSt;
       System.out.println("REST_URL " + REST_URL + "statistic/" + dateSort);
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "statistic/" + dateSort)
               .with(userHttpBasic(TestData.getForAuth())))
               .andExpect(status().isOk())
               .andDo(print())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(content().json(JsonUtil.writeValue(voteCheck)));
    }
}
