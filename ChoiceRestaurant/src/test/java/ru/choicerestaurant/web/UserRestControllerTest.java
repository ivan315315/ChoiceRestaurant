package ru.choicerestaurant.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.choicerestaurant.model.User;
import ru.choicerestaurant.repository.TestData;
import ru.choicerestaurant.service.UserServImpl;
import ru.choicerestaurant.to.UserTo;
import ru.choicerestaurant.util.exception.NotFoundException;
import ru.choicerestaurant.web.json.JsonUtil;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.choicerestaurant.DataGeneration.*;
import static ru.choicerestaurant.TestUtil.readFromJson;
import static ru.choicerestaurant.TestUtil.userHttpBasic;
import static ru.choicerestaurant.repository.TestData.*;


class UserRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserRestController.REST_URL + '/';

    @Autowired
    private UserServImpl userServImpl;

    @Test
    void create() throws Exception {
        UserTo newUserTo = getNewUser();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .with(userHttpBasic(TestData.getForAuth()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newUserTo)))
                .andExpect(status().isCreated());
        User created = readFromJson(action, User.class);
        Assertions.assertThat(created).isEqualToIgnoringGivenFields(userServImpl.get(created.getId()), "registered");
    }

    @Test
    void update() throws Exception {
        UserTo userTo = getRandomElement(userTos);
        UserTo userToUpdate = new UserTo(userTo.getId(), getNewString(userTo.getName()), getNewString(userTo.getEmail()),
                userTo.getPassword(), userTo.getRegistered(), userTo.getEnabled(), userTo.getRole());
        User user = userServImpl.getObjectByToObject(userToUpdate);
        ResultActions actionUpdate = perform(MockMvcRequestBuilders.put(REST_URL + user.getId())
                .with(userHttpBasic(TestData.getForAuth()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(userToUpdate)))
                .andExpect(status().isNoContent());
        Assertions.assertThat(user).isEqualTo(userServImpl.get(user.getId()));
    }

    @Test
    void delete() throws Exception {
        Integer id = getRandomElement(userTos).getId();
        perform(MockMvcRequestBuilders.delete(REST_URL + id)
                .with(userHttpBasic(TestData.getForAuth())))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> userServImpl.get(id));
    }

    @Test
    void get() throws Exception {
        UserTo userTo = getRandomElement(userTos);
        User user = userServImpl.getObjectByToObject(userTo);
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + user.getId())
                .with(userHttpBasic(TestData.getForAuth())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(JsonUtil.writeValue(user)));
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(TestData.getForAuth())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestData.userMatcher.contentJson(TestData.userTos.stream()
                        .map(userTo -> userServImpl.getObjectByToObject(userTo))
                        .collect(Collectors.toList()))
                );
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    /*@Test
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(TestData.getForAuth())))
                .andExpect(status().isForbidden());
                // todo
    }*/
}