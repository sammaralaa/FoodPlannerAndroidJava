package com.example.androidproject.view.meal_card;

import com.example.androidproject.model.categoriesModel.Category;
import com.example.androidproject.model.countriesModel.Country;
import com.example.androidproject.model.mealsModel.Meal;

import java.util.List;

public interface IMealCard {
    public void showData(List<Meal> meals);
    public void random(List<Meal> meals);
    public void showCtegories(List<Category> categories);
    public void showCountries(List<Meal> countries);
}
