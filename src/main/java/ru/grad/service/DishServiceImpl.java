package ru.grad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.grad.model.Dish;
import ru.grad.repository.DishRepository;

import ru.grad.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.grad.util.ValidationUtil.checkNotFoundWithId;


@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository repository;


    @Override
    public Dish save(Dish dish, int restaurantId) {
        Assert.notNull(dish, "deash must not be null");
        return repository.save(dish, restaurantId);
    }

    @Override
    public Dish get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    @Override
    public List<Dish> getMenu(int restaurantId) {
        LocalDate date = LocalDate.now();
        return repository.getMenu(restaurantId, date);
    }

    @Override
    public void delete(int id, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);

    }

    @Override
    public Dish update(Dish dish, int restaurantId) {
        Assert.notNull(dish, "deash must not be null");
        return checkNotFoundWithId(repository.save(dish, restaurantId), dish.getId());
    }

    @Override
    public void deleteRestaurantMenu(int restaurantId) throws NotFoundException {
        checkNotFoundWithId(repository.deleteRestaurantMenu(restaurantId), restaurantId);
    }

}
