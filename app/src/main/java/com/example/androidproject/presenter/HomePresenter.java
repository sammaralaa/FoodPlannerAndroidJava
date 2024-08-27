package com.example.androidproject.presenter;

import android.util.Log;

import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.model.categoriesModel.Category;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.network.FirebaseAuthManager;
import com.example.androidproject.network.MealsRemoteDataSource;
import com.example.androidproject.network.NetworkCallBack;
import com.example.androidproject.network.NetworkCallBackCategory;
import com.example.androidproject.network.NetworkCallBackCountry;
import com.example.androidproject.network.repository.MealsRepositoryImpl;
import com.example.androidproject.view.meal_card.IMealCard;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class HomePresenter implements NetworkCallBack , NetworkCallBackCategory , NetworkCallBackCountry {
    private IMealCard iView;
    private MealsRepositoryImpl repository;
    FirebaseAuthManager firebaseAuthManager;
    public HomePresenter(IMealCard iView , MealsLocalDataSource localDataSource,MealsRemoteDataSource mealsRemoteDataSource){
        this.iView=iView;
        repository = MealsRepositoryImpl.getInstance(localDataSource,mealsRemoteDataSource);
        firebaseAuthManager = new FirebaseAuthManager(FirebaseAuth.getInstance());
    }
    public HomePresenter(){
        firebaseAuthManager = new FirebaseAuthManager(FirebaseAuth.getInstance());
    }
    public void getMeals(){
       // ProductRepository.getAllProducts(this);
        repository.searchByCategory(this,"Beef");
    }

    public void getRandomMeal(){
        repository.getRandomMeal(this);
    }
    public void addToFav(Meal meal){
        repository.insertMealToFav(meal);
    }
    public void getAllCategories(){
        repository.getAllCategories(this);
    }

    public void listAllCountries(){
        repository.listAllCountries(this);
    }
    public void getMealById(String id){
        repository.getMealById(this,id);
    }

    public FirebaseUser getCurrentUserType() {
        return firebaseAuthManager.getCurrentUser();
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
