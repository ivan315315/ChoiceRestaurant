package ru.choicerestaurant.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.choicerestaurant.model.UserRestaurantDay;
import ru.choicerestaurant.repository.TestData;
import ru.choicerestaurant.service.UserRestaurantDayServ;
import ru.choicerestaurant.to.UserRestaurantDayTo;
import ru.choicerestaurant.web.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.choicerestaurant.TestUtil.userHttpBasic;
import static ru.choicerestaurant.repository.TestData.getNewUserRestaurantDay;

public class UserRestaurantDayRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = UserRestaurantDayRestController.REST_URL + '/';

    @Autowired
    private UserRestaurantDayServ userRestaurantDayServ;

    @Test
    void create() throws Exception {
        UserRestaurantDayTo newUserRestaurantDayTo = getNewUserRestaurantDay();
        UserRestaurantDay userRestaurantDayNew = userRestaurantDayServ.getObjectByToObject(newUserRestaurantDayTo);
        System.out.println("JsonUtil.writeValue" + JsonUtil.writeValue(newUserRestaurantDayTo));
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .with(userHttpBasic(TestData.getForAuth()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newUserRestaurantDayTo)))
                .andDo(print())
                .andExpect(status().isNoContent());
        //UserRestaurantDay created = readFromJson(action, UserRestaurantDay.class);//todo
        //Assertions.assertThat(created).isEqualToIgnoringGivenFields(userRestaurantDayNew);

    }
}
