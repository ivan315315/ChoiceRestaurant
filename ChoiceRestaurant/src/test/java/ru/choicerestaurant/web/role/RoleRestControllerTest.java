package ru.choicerestaurant.web.role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.choicerestaurant.DataGeneration;
import ru.choicerestaurant.repository.TestData;
import ru.choicerestaurant.service.RoleServ;
import ru.choicerestaurant.web.AbstractControllerTest;
import ru.choicerestaurant.web.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.choicerestaurant.TestUtil.userHttpBasic;
import static ru.choicerestaurant.repository.TestData.ADMIN;

class RoleRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RoleRestController.REST_URL + '/';

    @Autowired
    private RoleServ roleServ;

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + TestData.ADMIN_ID)
                .with(userHttpBasic(TestData.getForAuth())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(JsonUtil.writeIgnoreProps(ADMIN, "registered")));
    }

    @Test
    void getNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + DataGeneration.getNotExistNum(roleServ.getAll()))
                .with(userHttpBasic(TestData.getForAuth())))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(TestData.getForAuth())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestData.roleMatcher.contentJson(TestData.roles));
    }
}