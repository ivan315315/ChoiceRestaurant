package ru.choicerestaurant.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.choicerestaurant.model.Meal;
import ru.choicerestaurant.repository.TestData;
import ru.choicerestaurant.service.MealServ;
import ru.choicerestaurant.to.MealTo;
import ru.choicerestaurant.util.exception.NotFoundException;
import ru.choicerestaurant.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.choicerestaurant.DataGeneration.getNewString;
import static ru.choicerestaurant.DataGeneration.getRandomElement;
import static ru.choicerestaurant.TestUtil.readFromJson;
import static ru.choicerestaurant.TestUtil.userHttpBasic;
import static ru.choicerestaurant.repository.TestData.*;

public class MealRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MealRestController.REST_URL + '/';

    @Autowired
    private MealServ mealServ;

    @Test
    void create() throws Exception {
        MealTo newMealTo = getNewMeal();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .with(userHttpBasic(TestData.getForAuth()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMealTo)))
                .andExpect(status().isCreated());
        Meal created = readFromJson(action, Meal.class);
        int newId = created.getId();
        newMealTo.setId(newId);
        Assertions.assertThat(created).isEqualToIgnoringGivenFields(mealServ.get(newId), "restaurant");
    }

    @Test
    void update() throws Exception {
        MealTo mealTo = getRandomElement(mealTos);
        MealTo mealToUpdate = new MealTo(mealTo.getId(), getNewString(mealTo.getName()), mealTo.getPrice(), mealTo.getLunchDay(), mealTo.getRestaurantId());
        Meal meal = mealServ.getObjectByToObject(mealToUpdate);
        ResultActions actionUpdate = perform(MockMvcRequestBuilders.put(REST_URL + mealToUpdate.getId())
                .with(userHttpBasic(TestData.getForAuth()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(mealToUpdate)))
                .andExpect(status().isNoContent());
        Assertions.assertThat(meal).isEqualTo(mealServ.get(meal.getId()));
    }

    @Test
    void delete() throws Exception {
        Integer id = getRandomElement(mealTos).getId();
        perform(MockMvcRequestBuilders.delete(REST_URL + id)
                .with(userHttpBasic(TestData.getForAuth())))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> mealServ.get(id));
    }

    @Test
    void get() throws Exception {
        MealTo mealTo = getRandomElement(mealTos);
        Meal meal = mealServ.getObjectByToObject(mealTo);
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + meal.getId())
                .with(userHttpBasic(TestData.getForAuth())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(JsonUtil.writeValue(meal)));
    }
}
