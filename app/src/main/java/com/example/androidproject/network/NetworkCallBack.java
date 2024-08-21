package com.example.androidproject.network;

import com.example.androidproject.model.mealsModel.Meal;

import java.util.List;

public interface NetworkCallBack {
    public void onSuccessResult(List<Meal> meals);
    public void onFailureResult(String errorMsg);
}
