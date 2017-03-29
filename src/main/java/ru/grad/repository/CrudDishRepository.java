package ru.grad.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.grad.model.Dish;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends CrudRepository<Dish, Integer> {

    @Override
    Dish save(Dish dish);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId ORDER BY d.name")
    List<Dish> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.menudate=:menudate ORDER BY d.name")
    List<Dish> getAll(@Param("restaurantId") int restaurantId, @Param("menudate")LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId")int restaurantId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.restaurant.id=:restaurantId")
    int deleteRestaurantMenu(@Param("restaurantId") int restaurantId);

//    @Query("SELECT d FROM Dish d JOIN FETCH d.restaurant WHERE d.id = ?1 AND d.restaurant.id = ?2")
//    Dish getWithRestaurants(int id, int restaurantId);
}
