package ru.grad.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grad.model.Dish;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DishRepositoryImpl implements DishRepository {

    @Autowired
    private CrudDishRepository crudDishRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudDishRepository.save(dish);
    }

    @Override
    public int delete(int id, int restaurantId) {
        return crudDishRepository.delete(id, restaurantId);
    }

    @Override
    public Dish get(int id, int restaurantId) {
        Dish dish = crudDishRepository.findOne(id);
        return dish != null && dish.getRestaurant().getId() == restaurantId ? dish : null;
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return crudDishRepository.getAll(restaurantId);
    }

    @Override
    public List<Dish> getMenu(int restaurantId, LocalDate date) {
        return crudDishRepository.getAll(restaurantId, date);
    }

    @Override
    public int deleteRestaurantMenu(int restaurantId) {
        return crudDishRepository.deleteRestaurantMenu(restaurantId);
    }

//    @Override
//    public Dish getWithRestaurant(int id, int restaurantId) {
//        return crudDishRepository.getWithRestaurants(id, restaurantId);
//    }
}
