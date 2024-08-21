package com.example.androidproject.model.mealsModel;

import java.util.List;

public class MealResponse {
    public List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
