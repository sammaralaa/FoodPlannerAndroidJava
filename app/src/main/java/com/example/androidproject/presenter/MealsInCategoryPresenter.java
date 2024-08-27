package com.example.androidproject.presenter;

import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.network.FirebaseAuthManager;
import com.example.androidproject.network.MealsRemoteDataSource;
import com.example.androidproject.network.NetworkCallBack;
import com.example.androidproject.network.repository.MealsRepositoryImpl;
import com.example.androidproject.view.category_card.IMealsInCategory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MealsInCategoryPresenter implements NetworkCallBack {
    IMealsInCategory iView;
    MealsRepositoryImpl repository;
    FirebaseAuthManager firebaseAuthManager;
    public MealsInCategoryPresenter(IMealsInCategory iView){
        this.iView = iView;
        firebaseAuthManager= new FirebaseAuthManager(FirebaseAuth.getInstance());
    }
    public MealsInCategoryPresenter(IMealsInCategory iView,MealsLocalDataSource localDataSource,MealsRemoteDataSource mealsRemoteDataSource){
        this.iView = iView;
        repository = MealsRepositoryImpl.getInstance(localDataSource,mealsRemoteDataSource);
        firebaseAuthManager= new FirebaseAuthManager(FirebaseAuth.getInstance());
    }
    public void getAllMeals(String category){
        repository.searchByCategory(this,category);
    }

    public void getAllMealsOnCountry(String country){
        repository.searchByCountry(this,country);
    }
    public void addToFav(Meal meal){
        repository.insertMealToFav(meal);
    }

    public FirebaseUser getCurrentUserType() {
        return firebaseAuthManager.getCurrentUser();
    }

    @Override
    public void onSuccessResult(List<Meal> meals) {
        iView.showMealsList(meals);

    }

    @Override
    public void onFailureResult(String errorMsg) {

    }
}
