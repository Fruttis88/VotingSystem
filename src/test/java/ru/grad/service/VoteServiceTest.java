package ru.grad.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.grad.model.Vote;
import ru.grad.util.exception.CantVoteException;

import java.util.Arrays;

import static ru.grad.RestaurantTestData.*;
import static ru.grad.UserTestData.ADMIN_ID;
import static ru.grad.VoteTestData.MATCHER;
import static ru.grad.UserTestData.USER_ID;
import static ru.grad.VoteTestData.VOTE1;


public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    protected VoteService service;
    @Autowired
    protected RestaurantService restaurantService;

    @Test
    public void testVote() throws Exception{
        Vote newVote = service.save(RES1_ID, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE1, newVote), service.getAll());
        ru.grad.RestaurantTestData.MATCHER.assertCollectionEquals(Arrays.asList(RES3, getVoted(), RES2), restaurantService.getAll());
    }

    @Test
    public void testVoteTwoTimes() throws Exception{
        Vote newVote = service.save(RES1_ID, USER_ID);
        Vote newVote2 = service.save(RES1_ID, ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE1, newVote, newVote2), service.getAll());
        ru.grad.RestaurantTestData.MATCHER.assertCollectionEquals(Arrays.asList(RES3, getVotedSecondTime(), RES2), restaurantService.getAll());
    }

//    When current time > 11 o`clock
    @Test
    public void testLateVote() throws Exception{
        thrown.expect(CantVoteException.class);
        Vote newVote = service.save(RES1_ID, USER_ID);
        Vote newVote2 = service.save(RES2_ID, USER_ID);
    }

}
