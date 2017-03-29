package ru.grad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.grad.model.Vote;
import ru.grad.model.VoteResult;
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

    @Autowired
    public VoteServiceImpl(CrudRestaurantRepository restaurantRepository, CrudVoteRepository repository, CrudUserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.repository = repository;
        this.userRepository = userRepository;
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
        voteToday.setRestaurant(restaurantRepository.getOne(restaurantId));
        voteToday.setUser(userRepository.getOne(userId));
        repository.save(voteToday);
        return voteToday;
    }

    @Override
    public List<VoteResult> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

//    using only for testing
    @Override
    public List<Vote> getAll() {
        return repository.findAll();
    }
}
