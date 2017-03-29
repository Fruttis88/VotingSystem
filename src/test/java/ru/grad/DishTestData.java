package ru.grad;

import ru.grad.matcher.ModelMatcher;
import ru.grad.model.Dish;
import java.util.Arrays;
import java.util.List;

import static ru.grad.model.BaseEntity.START_SEQ;


public class DishTestData {
    public static final ModelMatcher<Dish> MATCHER = ModelMatcher.of(Dish.class);

    public static final int DISH_RES1_ID = START_SEQ + 6;
    public static final int DISH_RES2_ID = START_SEQ + 8;
    public static final int DISH_RES3_ID = START_SEQ + 10;

    public static final Dish DISH1 = new Dish(DISH_RES1_ID, "Шашлык", 500);
    public static final Dish DISH2 = new Dish(DISH_RES1_ID+1, "Машлык", 1000);
    public static final Dish DISH3 = new Dish(DISH_RES2_ID, "Супец", 350);
    public static final Dish DISH4 = new Dish(DISH_RES2_ID+1, "Холодец", 400);
    public static final Dish DISH5 = new Dish(DISH_RES3_ID, "Кукиш с маслом", 240);
    public static final Dish DISH6 = new Dish(DISH_RES3_ID+1, "Гальваническая развязка из желе", 12000);

    public static final List<Dish> DISHES_RES2 = Arrays.asList(DISH3, DISH4);

    public static Dish getCreated() {
        return new Dish(null, "Созданная еда", 0);
    }

    public static Dish getUpdated() {
        return new Dish(DISH_RES2_ID, "Обновленная еда", 0);
    }
}