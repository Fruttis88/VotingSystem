package ru.grad.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.grad.RestaurantTestData;
import ru.grad.model.Dish;
import ru.grad.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;

import static ru.grad.DishTestData.*;
import static ru.grad.RestaurantTestData.RES2_ID;


public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    protected DishService service;

    @Test
    public void testSave() throws Exception {
        Dish created = getCreated();
        service.save(created, RES2_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(created, DISH3, DISH4), service.getAll(RES2_ID));
    }
    @Test
    public void testDelete() throws Exception {
        service.delete(DISH_RES2_ID, RES2_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(DISH4), service.getAll(RES2_ID));
    }
    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(DISH_RES2_ID, 77);
    }
    @Test
    public void testGet() throws Exception {
        Dish actual = service.get(DISH_RES2_ID+1, RES2_ID);
        MATCHER.assertEquals(DISH4, actual);
    }
    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(DISH_RES3_ID, RES2_ID);
    }
    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(DISHES_RES2, service.getAll(RES2_ID));
    }
    @Test
    public void testUpdate() throws Exception {
        Dish updated = getUpdated();
        service.update(updated, RES2_ID);
        MATCHER.assertEquals(updated, service.get(DISH_RES2_ID, RES2_ID));
    }
    @Test
    public void testUpdateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + DISH_RES1_ID);
        service.update(DISH1, RES2_ID);
    }
//    @Test
//    public void testGetWithRestaurant() throws Exception {
//        Dish restaurant2Dish = service.getWithRestaurant(DISH_RES2_ID, RES2_ID);
//        MATCHER.assertEquals(DISH3, restaurant2Dish);
//        RestaurantTestData.MATCHER.assertEquals(RestaurantTestData.RES2, restaurant2Dish.getRestaurant());
//    }
//    @Test(expected = NotFoundException.class)
//    public void testGetWithRestaurantNotFound() throws Exception {
//        service.getWithRestaurant(DISH_RES1_ID, RES2_ID);
//    }
    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.save(new Dish(null, "  ", 0), RES2_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.save(new Dish(null, "Dish", null), RES2_ID), ConstraintViolationException.class);
    }
}
