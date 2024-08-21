package com.example.androidproject.network;

import com.example.androidproject.model.categoriesModel.Category;

import java.util.List;

public interface NetworkCallBackCategory {
    public void onSuccessResultCategory(List<Category> categories);
    public void onFailureResultCategory(String errorMsg);
}
