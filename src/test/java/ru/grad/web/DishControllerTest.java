package ru.grad.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import ru.grad.model.Dish;
import ru.grad.service.DishService;
import ru.grad.web.json.JsonUtil;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static ru.grad.DishTestData.*;
import static ru.grad.RestaurantTestData.RES1_ID;
import static ru.grad.TestUtil.userHttpBasic;
import static ru.grad.UserTestData.ADMIN;


public class DishControllerTest extends AbstractControllerTest {

    private static final String URL = DishController.URL + "/";

    @Autowired
    private DishService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(URL +  RES1_ID + "/dishes/" + DISH_RES1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(DISH1));
    }

    @Test
    public void testGetAll() throws Exception{
        mockMvc.perform(get(URL + RES1_ID + "/dishes/all")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(DISH2, DISH1));
    }

    @Test
    public void testGetMenu() throws Exception{
        mockMvc.perform(get(URL + RES1_ID + "/dishes")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(DISH2, DISH1));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(URL + RES1_ID + "/dishes/10000")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(URL + RES1_ID + "/dishes/" + DISH_RES1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Transactional
    @Test
    public void testCreate() throws Exception {
        Dish expected = new Dish(null, "newDish", 800);
        ResultActions action = mockMvc.perform(post(URL + RES1_ID + "/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated());

        Dish returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(expected, DISH2, DISH1), service.getAll(RES1_ID));
    }

    @Test
    public void testCreateInvalid() throws Exception {
        Dish dish = new Dish(null, " ", 750);
        mockMvc.perform(post(URL + RES1_ID + "/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(dish)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Transactional
    @Test
    public void testUpdate() throws Exception {
        Dish updated = new Dish(DISH1);
        updated.setName("update");
        updated.setPrice(1488);
        mockMvc.perform(put(URL + RES1_ID + "/dishes/" + DISH_RES1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, service.get(DISH_RES1_ID, RES1_ID));
    }

    @Test
    public void testUpdateInvalid() throws Exception {
        Dish updated = new Dish(DISH1);
        updated.setName(" ");
        updated.setPrice(-900);
        mockMvc.perform(put(URL + RES1_ID + "/dishes/" + DISH_RES1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Transactional
    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(URL + RES1_ID + "/dishes/" + DISH_RES1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Collections.singletonList(DISH2), service.getAll(RES1_ID));
    }

    @Transactional
    @Test
    public void testDeleteMenu() throws Exception {
        mockMvc.perform(delete(URL + RES1_ID + "/dishes")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Collections.emptyList(), service.getAll(RES1_ID));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(URL + RES1_ID + "/dishes/" + DISH_RES2_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testDeleteMenuNotFound() throws Exception {
        mockMvc.perform(delete(URL + "700/dishes")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }


}
