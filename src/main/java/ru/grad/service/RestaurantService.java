package ru.grad.service;

import ru.grad.model.Restaurant;
import ru.grad.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {

    Restaurant save(Restaurant restaurant);

    Restaurant get(int id) throws NotFoundException;

    List<Restaurant> getAll();

    void delete(int id) throws NotFoundException;

    void update(Restaurant restaurant);

    void evictCache();
}
