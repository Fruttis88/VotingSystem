package ru.grad.service;


import ru.grad.model.Vote;
import ru.grad.model.VoteResult;

import java.util.List;

public interface VoteService {

    Vote save(int restaurantId, int userId);

    List<VoteResult> getAll(int restaurantId);

    List<Vote> getAll();
}
