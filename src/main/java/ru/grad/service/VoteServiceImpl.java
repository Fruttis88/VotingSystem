package ru.grad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.grad.model.Restaurant;
import ru.grad.model.Vote;
import ru.grad.repository.CrudRestaurantRepository;
import ru.grad.repository.CrudUserRepository;
import ru.grad.repository.CrudVoteRepository;
import ru.grad.util.exception.CantVoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
public class VoteServiceImpl implements VoteService {

    private final CrudVoteRepository repository;
    private final CrudRestaurantRepository restaurantRepository;
    private final CrudUserRepository userRepository;
    private final RestaurantService restaurantService;

    @Autowired
    public VoteServiceImpl(CrudRestaurantRepository restaurantRepository, CrudVoteRepository repository, CrudUserRepository userRepository, RestaurantService restaurantService) {
        this.restaurantRepository = restaurantRepository;
        this.repository = repository;
        this.userRepository = userRepository;
        this.restaurantService = restaurantService;
    }

    @Override
    public Vote save(int restaurantId, int userId) {
        LocalDate today = LocalDate.now();
        LocalTime endingVoteTime = LocalTime.of(11,0);
        Vote voteToday = repository.getUserVotesToday(userId, today);
        if (voteToday != null) {
            if(LocalTime.now().isAfter(endingVoteTime)) {
                throw new CantVoteException("too late to vote");
            }
        } else {
            voteToday = new Vote();
        }
        Restaurant r = restaurantRepository.findOne(restaurantId);
        voteToday.setRestaurant(r);
        voteToday.setUser(userRepository.getOne(userId));
        repository.save(voteToday);
        restaurantService.countVotes(r);
        return voteToday;
    }

//    using only for testing
    @Override
    public List<Vote> getAll() {
        return repository.getAll();
    }
}
