package ru.grad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grad.matcher.ModelMatcher;
import ru.grad.model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static ru.grad.model.BaseEntity.START_SEQ;


public class RestaurantTestData {
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantTestData.class);
    public static final ModelMatcher<Restaurant> MATCHER = ModelMatcher.of(Restaurant.class);

    public static final int RES1_ID = START_SEQ + 3;
    public static final int RES2_ID = START_SEQ + 4;
    public static final int RES3_ID = START_SEQ + 5;

    public static final Restaurant RES1 = new Restaurant(RES1_ID, "Жрем днем", 0);
    public static final Restaurant RES2 = new Restaurant(RES2_ID, "Хочу харчо", 1);
    public static final Restaurant RES3 = new Restaurant(RES3_ID, "На парах", 0);

    public static Restaurant getCreated() {
        return new Restaurant(null, "Созданный рестик", 0);
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RES2_ID, "Обновленный рестик", 0);
    }

    public static Restaurant getVoted() {
        return new Restaurant(RES1_ID, "Жрем днем", 1);
    }

    public static Restaurant getVotedSecondTime() {
        return new Restaurant(RES1_ID, "Жрем днем", 2);
    }

}