package ru.grad.service;


import ru.grad.model.Vote;

import java.util.List;

public interface VoteService {
    Vote save(int restaurantId, int userId);

    List<Vote> getAll();
}
