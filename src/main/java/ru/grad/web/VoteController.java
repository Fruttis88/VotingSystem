package ru.grad.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grad.AuthorizedUser;
import ru.grad.service.VoteService;

@RestController
@RequestMapping(value = VoteController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    private static final Logger LOG = LoggerFactory.getLogger(VoteController.class);

    static final String URL = "/api/v1/votes";

    @Autowired
    private VoteService service;

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@PathVariable("restaurantId") int restaurantId){
        int userId = AuthorizedUser.id();
        LOG.info("New vote by restaurant with id {} for User {}", restaurantId, userId);
        service.save(restaurantId, userId);
    }
}
