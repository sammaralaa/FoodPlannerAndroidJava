package com.example.androidproject.view.favorites;

import com.example.androidproject.model.mealsModel.Meal;

public interface OnFavClickListener {
    public void onFaveMealClick(Meal meal);
    public void onDeleteFavMealClick(Meal meal);
}
