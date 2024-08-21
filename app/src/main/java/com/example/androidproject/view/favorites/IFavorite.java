package com.example.androidproject.view.favorites;

import androidx.lifecycle.LiveData;

import com.example.androidproject.model.mealsModel.Meal;

import java.util.List;

public interface IFavorite {
    public void showFavMeals(LiveData<List<Meal>> meals);
}
