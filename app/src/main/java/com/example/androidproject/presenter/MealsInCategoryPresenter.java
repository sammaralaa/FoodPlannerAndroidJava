package com.example.androidproject.presenter;

import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.network.MealsRemoteDataSource;
import com.example.androidproject.network.NetworkCallBack;
import com.example.androidproject.view.category_card.IMealsInCategory;

import java.util.List;

public class MealsInCategoryPresenter implements NetworkCallBack {
    IMealsInCategory iView;
    MealsRemoteDataSource mealsRemoteDataSource = MealsRemoteDataSource.getInstance();
    MealsLocalDataSource localDataSource;
    public MealsInCategoryPresenter(IMealsInCategory iView){
        this.iView = iView;
    }
    public MealsInCategoryPresenter(IMealsInCategory iView,MealsLocalDataSource localDataSource){
        this.iView = iView;
        this.localDataSource=localDataSource;
    }
    public void getAllMeals(String category){
        mealsRemoteDataSource.searchByCategoryCall(this,category);
    }

    public void getAllMealsOnCountry(String country){
        mealsRemoteDataSource.searchByCountryCall(this,country);
    }
    public void addToFav(Meal meal){
        localDataSource.insert(meal);
    }
    @Override
    public void onSuccessResult(List<Meal> meals) {
        iView.showMealsList(meals);

    }

    @Override
    public void onFailureResult(String errorMsg) {

    }
}
