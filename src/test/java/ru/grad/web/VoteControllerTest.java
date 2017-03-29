package ru.grad.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import ru.grad.model.Vote;
import ru.grad.service.VoteService;
import ru.grad.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.grad.RestaurantTestData.RES1_ID;
import static ru.grad.RestaurantTestData.RES2_ID;
import static ru.grad.TestUtil.userHttpBasic;
import static ru.grad.UserTestData.USER;
import static ru.grad.VoteTestData.MATCHER_VOTE_RESULT;
import static ru.grad.VoteTestData.VOTES_RESULT1;

public class VoteControllerTest extends AbstractControllerTest {

    private static final String URL = VoteController.URL + "/";

    @Transactional
    @Test
    public void testAdd() throws Exception {
        Vote vote = new Vote(null);
        mockMvc.perform(post(URL + RES1_ID + "/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(vote)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetResults() throws Exception {
        mockMvc.perform(get(URL + RES2_ID + "/votes")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_VOTE_RESULT.contentListMatcher(VOTES_RESULT1));
    }
}
