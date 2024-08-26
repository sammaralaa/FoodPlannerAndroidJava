package com.example.androidproject.network.repository;

import androidx.lifecycle.LiveData;

import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMeal;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetails;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.network.NetworkCallBack;
import com.example.androidproject.network.NetworkCallBackCategory;
import com.example.androidproject.network.NetworkCallBackCountry;

import java.util.List;

public interface MealsRepository {
    LiveData<List<Meal>> getLocalMeals();

    void removeMealFromFav(Meal meal);

    void insertMealToFav(Meal meal);

    void insertPlanMeal(WeeklyPlanMeal meal, WeeklyPlanMealDetails mealDetails);

    LiveData<List<WeeklyPlanMeal>> getLocalPlanMeals();

    WeeklyPlanMealDetails getMealByID(String id);

    void removeMealFromPlan(WeeklyPlanMeal meal);

    void listAllCountries(NetworkCallBackCountry networkCallback);

    void searchByCategory(NetworkCallBack networkCallback, String category);

    void searchByCountry(NetworkCallBack networkCallback, String country);

    void searchByIngredient(NetworkCallBack networkCallback, String ingredient);

    void getRandomMeal(NetworkCallBack networkCallback);

    void getMealById(NetworkCallBack networkCallback, String id);

    void getAllCategories(NetworkCallBackCategory networkCallback);
}
