package ru.grad.repository;

import ru.grad.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {
    Dish save(Dish dish, int restaurantId);

    int delete(int id, int restaurantId);

    Dish get(int id, int restaurantId);

    List<Dish> getAll(int restaurantId);

    List<Dish> getMenu(int restaurantId, LocalDate date);

    public int deleteRestaurantMenu(int restaurantId);

//    default Dish getWithRestaurant(int id, int restaurantId) {
//        throw new UnsupportedOperationException();
//    }
}
