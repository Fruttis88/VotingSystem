package ru.grad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.grad.model.Restaurant;
import ru.grad.repository.CrudRestaurantRepository;
import ru.grad.util.exception.NotFoundException;
import java.util.List;
import static ru.grad.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private CrudRestaurantRepository repository;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Override
    public Restaurant countVotes(Restaurant restaurant) {
        restaurant.setVotescount(restaurant.getVotescount() + 1);
        return repository.save(restaurant);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.findOne(id), id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAllWithDishes();
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        repository.save(restaurant);
    }

}
