package ru.grad.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.grad.DishTestData;
import ru.grad.model.Restaurant;
import ru.grad.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;

import static ru.grad.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest{

    @Autowired
    protected RestaurantService service;

    @Before
    public void setUp() throws Exception {
        service.evictCache();
    }

    @Test
    public void testSave() throws Exception{
        Restaurant newRestaurant = getCreated();
        service.save(newRestaurant);
        MATCHER.assertCollectionEquals(Arrays.asList(RES3, RES1, RES2, newRestaurant), service.getAll());
    }
    @Test
    public void testDelete() throws Exception {
        service.delete(RES2_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(RES3, RES1), service.getAll());
    }
    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(88);
    }
    @Test
    public void testGet() throws Exception {
        Restaurant restaurant = service.get(RES1_ID);
        MATCHER.assertEquals(RES1, restaurant);
    }
    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(88);
    }
    @Test
    public void testGetAll() throws Exception {
        Collection<Restaurant> all = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(RES3, RES1, RES2), all);
    }
    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = getUpdated();
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(RES2_ID));
    }

}
