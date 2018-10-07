package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

public interface DaoMeals {
    public List<MealWithExceed> getFilteredWithExceeded();
}
