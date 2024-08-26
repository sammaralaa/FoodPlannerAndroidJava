package com.example.androidproject.presenter;

import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.network.MealsRemoteDataSource;
import com.example.androidproject.network.NetworkCallBack;
import com.example.androidproject.view.search.ISearchView;

import java.util.List;

public class SearchPresenter implements NetworkCallBack {
    ISearchView iView;
    MealsRemoteDataSource mealsRemoteDataSource = MealsRemoteDataSource.getInstance();

    public SearchPresenter(ISearchView iView,MealsRemoteDataSource mealsRemoteDataSource){
        this.iView=iView;
        this.mealsRemoteDataSource=mealsRemoteDataSource;
    }
    public void searchByCategory(String category){
        mealsRemoteDataSource.searchByCategoryCall(this,category);
    }

    public void searchByCountry(String country){
        mealsRemoteDataSource.searchByCountryCall(this,country);
    }

    public void searchByIngrediant(String ingrediant){
        mealsRemoteDataSource.searchByIngredientCall(this,ingrediant);
    }
    @Override
    public void onSuccessResult(List<Meal> meals) {
        iView.searchResault(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) {

    }
}
