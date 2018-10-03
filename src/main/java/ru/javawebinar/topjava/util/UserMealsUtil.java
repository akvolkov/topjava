package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        Map<LocalDate, Integer> mapCalloriesPerDate = new HashMap<>();
        for (UserMeal userMeal:mealList
             ) {
            LocalDate localDate = userMeal.getDateTime().toLocalDate();
            if (!mapCalloriesPerDate.containsKey(localDate)) {
                mapCalloriesPerDate.put(localDate, userMeal.getCalories());
            }
            else {
                int sumCallories = mapCalloriesPerDate.get(localDate);
                sumCallories += userMeal.getCalories();
                mapCalloriesPerDate.put(localDate, sumCallories);
            }
        }
        List<UserMealWithExceed> userMealWithExceedList = new ArrayList<UserMealWithExceed>();
        for (UserMeal userMeal:mealList
             ) {
            LocalDateTime localDateTime = userMeal.getDateTime();
            if (TimeUtil.isBetween(localDateTime.toLocalTime(), startTime, endTime)){
                if (mapCalloriesPerDate.get(localDateTime.toLocalDate()) > caloriesPerDay) {
                    userMealWithExceedList.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(),
                            userMeal.getCalories(), false));
                }
                else {
                    userMealWithExceedList.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(),
                            userMeal.getCalories(), true));
                }
            }
        }
        return userMealWithExceedList;
    }
}
