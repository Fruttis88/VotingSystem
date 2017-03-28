package ru.grad.service;

import ru.grad.model.Dish;
import ru.grad.util.exception.NotFoundException;
import java.util.List;

public interface DishService {
    Dish save(Dish dish, int restaurantId);

    Dish get(int id, int restaurantId) throws NotFoundException;

    List<Dish> getAll(int restaurantId);

    void delete(int id, int restaurantId) throws NotFoundException;

    Dish update(Dish dish, int restaurantId);

    void deleteRestaurantMenu(int restaurantId) throws NotFoundException;

}
