package com.example.androidproject.presenter;

import android.util.Log;

import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.model.categoriesModel.Category;
import com.example.androidproject.model.countriesModel.Country;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.network.MealsRemoteDataSource;
import com.example.androidproject.network.NetworkCallBack;
import com.example.androidproject.network.NetworkCallBackCategory;
import com.example.androidproject.network.NetworkCallBackCountry;
import com.example.androidproject.view.meal_card.IMealCard;

import java.util.List;

public class HomePresenter implements NetworkCallBack , NetworkCallBackCategory , NetworkCallBackCountry {
    private IMealCard iView;
    private MealsRemoteDataSource mealsRemoteDataSource = MealsRemoteDataSource.getInstance();
    private MealsLocalDataSource localDataSource;
    public HomePresenter(IMealCard iView , MealsLocalDataSource localDataSource){
        this.iView=iView;
        this.localDataSource = localDataSource;
    }
    public void getMeals(){
       // ProductRepository.getAllProducts(this);
        mealsRemoteDataSource.searchByCategoryCall(this,"Beef");
    }

    public void getRandomMeal(){
        mealsRemoteDataSource.getRandomMealCall(this);
    }
    public void addToFav(Meal meal){
        localDataSource.insert(meal);
    }
    public void getAllCategories(){
        mealsRemoteDataSource.getAllCategoriesCall(this);
    }

    public void listAllCountries(){
        mealsRemoteDataSource.makeNetworkCall(this);
    }
    @Override
    public void onSuccessResult(List<Meal> meals) {
        if(meals.size()==1){
            iView.random(meals);
        }
        else if(meals.size()>1){
            iView.showData(meals);
        } else if (meals.get(0).strCategory.equals(null)) {
            Log.i("API_CLIENT", "onSuccessResult: strCategory = null");
          iView.showCountries(meals);
        }
    }

    @Override
    public void onFailureResult(String errorMsg) {

    }


    @Override
    public void onSuccessResultCategory(List<Category> categories) {
        iView.showCtegories(categories);
    }

    @Override
    public void onFailureResultCategory(String errorMsg) {

    }

    @Override
    public void onSuccessResultCountry(List<Meal> countries) {
//        Log.i("TAG", "onSuccessResultCountry: " + countries.size());
        iView.showCountries(countries);
    }

    @Override
    public void onFailureResultCountry(String errorMsg) {

    }
}
