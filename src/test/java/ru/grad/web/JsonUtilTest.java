package ru.grad.web;

import org.junit.Test;
import ru.grad.DishTestData;
import ru.grad.model.Dish;
import ru.grad.web.json.JsonUtil;

import java.util.List;

public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(DishTestData.DISH5);
        System.out.println(json);
        Dish dish = JsonUtil.readValue(json, Dish.class);
        DishTestData.MATCHER.assertEquals(DishTestData.DISH5, dish);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(DishTestData.DISHES_RES2);
        System.out.println(json);
        List<Dish> dishes = JsonUtil.readValues(json, Dish.class);
        DishTestData.MATCHER.assertCollectionEquals(DishTestData.DISHES_RES2, dishes);
    }
}