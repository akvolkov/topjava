package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class MealsList implements DaoMeals {
    @Override
    public List<MealWithExceed> getFilteredWithExceeded() {
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
                );
        final int calloriesPerDate = 2000;
        List<MealWithExceed> mealWithExceedList = new ArrayList<MealWithExceed>();
        for (Meal meal:meals
             ) {
            if (caloriesSumByDate.get(meal.getDate()) > calloriesPerDate) {
                mealWithExceedList.add(
                        new MealWithExceed(
                                meal.getDateTime(), meal.getDescription(), meal.getCalories(),true));
            }
            else {mealWithExceedList.add(
                    new MealWithExceed(
                            meal.getDateTime(), meal.getDescription(), meal.getCalories(), false));

            }
        }
        return mealWithExceedList;
    }
}
