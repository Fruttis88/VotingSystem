package ru.grad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grad.matcher.ModelMatcher;
import ru.grad.model.Vote;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static ru.grad.model.BaseEntity.START_SEQ;


public class VoteTestData {
    private static final Logger LOG = LoggerFactory.getLogger(VoteTestData.class);
    public static final ModelMatcher<Vote> MATCHER = ModelMatcher.of(Vote.class);

    public static final int VOTE1_ID = START_SEQ + 12;

    public static final Vote VOTE1 = new Vote(VOTE1_ID);

}