package ru.grad;


import ru.grad.matcher.ModelMatcher;
import ru.grad.model.Restaurant;

import static ru.grad.model.BaseEntity.START_SEQ;


public class RestaurantTestData {
    public static final ModelMatcher<Restaurant> MATCHER = ModelMatcher.of(Restaurant.class);

    public static final int RES1_ID = START_SEQ + 3;
    public static final int RES2_ID = START_SEQ + 4;
    public static final int RES3_ID = START_SEQ + 5;

    public static final Restaurant RES1 = new Restaurant(RES1_ID, "Жрем днем");
    public static final Restaurant RES2 = new Restaurant(RES2_ID, "Хочу харчо");
    public static final Restaurant RES3 = new Restaurant(RES3_ID, "На парах");

    public static Restaurant getCreated() {
        return new Restaurant(null, "Созданный рестик");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RES2_ID, "Обновленный рестик");
    }

}