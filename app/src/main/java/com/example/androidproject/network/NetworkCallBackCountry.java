package com.example.androidproject.network;

import com.example.androidproject.model.countriesModel.Country;
import com.example.androidproject.model.mealsModel.Meal;

import java.util.List;

public interface NetworkCallBackCountry {
    public void onSuccessResultCountry(List<Meal> countries);
    public void onFailureResultCountry(String errorMsg);
}
