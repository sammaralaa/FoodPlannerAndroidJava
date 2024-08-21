package com.example.androidproject.view.search;

import com.example.androidproject.model.mealsModel.Meal;

import java.util.List;

public interface ISearchView {
    public void searchResault(List<Meal> meals);
    public void onSearchFailure(String msg);
}
