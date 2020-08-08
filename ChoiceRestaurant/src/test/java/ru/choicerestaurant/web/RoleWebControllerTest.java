package ru.choicerestaurant.web;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static ru.choicerestaurant.model.BaseEntity.START_SEQ;

public class RoleWebControllerTest extends AbstractControllerTest {

    @Test
    public void getRoles() throws Exception {
        mockMvc.perform(get("/roles"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("roles"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/roles.jsp"))
                .andExpect(model().attribute("roles", hasSize(2)))
                .andExpect(model().attribute("roles", hasItem(
                        allOf(
                                hasProperty("id", is(START_SEQ)),
                                hasProperty("name", is("Admin"))
                ))));
    }
}