package ru.grad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.grad.model.Restaurant;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);

    @Override
    Restaurant getOne(Integer id);

    @Override
    Restaurant findOne(Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);


    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.dishes")
    List<Restaurant> getAllWithDishes();

}
