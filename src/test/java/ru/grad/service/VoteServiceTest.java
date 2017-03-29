package ru.grad.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.grad.model.Vote;
import ru.grad.model.VoteResult;
import ru.grad.util.exception.CantVoteException;

import java.util.Arrays;
import java.util.List;

import static ru.grad.RestaurantTestData.*;
import static ru.grad.UserTestData.ADMIN_ID;
import static ru.grad.UserTestData.USER_ID;
import static ru.grad.VoteTestData.*;
import static ru.grad.VoteTestData.MATCHER;


public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    protected VoteService service;

    @Test
    public void testVote() throws Exception{
        Vote newVote = service.save(RES1_ID, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE1, VOTE2, newVote), service.getAll());
    }


//    When current time > 11 o`clock
    @Test
    public void testLateVote() throws Exception{
        thrown.expect(CantVoteException.class);
        Vote newVote = service.save(RES1_ID, ADMIN_ID);
        Vote newVote2 = service.save(RES3_ID, ADMIN_ID);
    }

    @Test
    public void testGetResults() throws Exception {
        List<VoteResult> current = service.getAll(RES2_ID);
        MATCHER_VOTE_RESULT.assertCollectionEquals(Arrays.asList(VOTES_RESULT1), current);
    }

}
