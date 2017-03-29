package ru.grad;

import ru.grad.matcher.ModelMatcher;
import ru.grad.model.Vote;
import ru.grad.model.VoteResult;
import java.time.LocalDate;
import java.util.Objects;

import static ru.grad.model.BaseEntity.START_SEQ;


public class VoteTestData {
    public static final ModelMatcher<Vote> MATCHER = ModelMatcher.of(Vote.class);

    public static final int VOTE1_ID = START_SEQ + 12;

    public static final Vote VOTE1 = new Vote(VOTE1_ID);
    public static final Vote VOTE2 = new Vote(VOTE1_ID+1);
    public static final VoteResult VOTES_RESULT1 = new VoteResult("Хочу харчо", 2L, LocalDate.now());

    public static final ModelMatcher<VoteResult> MATCHER_VOTE_RESULT = ModelMatcher.of(VoteResult.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getVoteDate(), actual.getVoteDate())
                            && Objects.equals(expected.getRestaurantName(), actual.getRestaurantName())
                            && Objects.equals(expected.getVoteCount(), actual.getVoteCount())
                    )
    );

}