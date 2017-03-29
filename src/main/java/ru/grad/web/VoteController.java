package ru.grad.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.grad.AuthorizedUser;
import ru.grad.model.Vote;
import ru.grad.model.VoteResult;
import ru.grad.service.VoteService;

import java.util.List;

@RestController
@RequestMapping(value = VoteController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    private static final Logger LOG = LoggerFactory.getLogger(VoteController.class);

    static final String URL = "/api/v1/restaurants";

    @Autowired
    private VoteService service;

    @PostMapping(value = "/{restaurantId}/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@PathVariable("restaurantId") int restaurantId){
        int userId = AuthorizedUser.id();
        LOG.info("New vote by restaurant with id {} for User {}", restaurantId, userId);
        service.save(restaurantId, userId);
    }

    @GetMapping(value = "/{restaurantId}/votes")
    public List<VoteResult> getResults(@PathVariable("restaurantId") int restaurantId){
        LOG.info("Get vote results by restaurant with id {}", restaurantId);
        return service.getAll(restaurantId);
    }
}
