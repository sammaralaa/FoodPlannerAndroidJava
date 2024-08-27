package com.example.androidproject.presenter;

import android.util.Log;

import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMeal;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetails;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.network.FirebaseAuthManager;
import com.example.androidproject.network.MealsRemoteDataSource;
import com.example.androidproject.network.NetworkCallBack;
import com.example.androidproject.network.repository.MealsRepositoryImpl;
import com.example.androidproject.view.mealDetails.IMealDetails;
import com.example.androidproject.view.meal_card.IMealCard;
import com.example.androidproject.view.weekly_plan.IWeeklyPlanMealDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MealDetailsPresenter implements NetworkCallBack {

    private IMealDetails iView;
    MealsRepositoryImpl repository;
    private IWeeklyPlanMealDetails iWeeklyPlanMealDetails;
    private FirebaseAuthManager firebaseAuthManager;
    private IMealCard iMealCard;

    public MealDetailsPresenter(IMealDetails iView , MealsLocalDataSource localDataSource,MealsRemoteDataSource mealsRemoteDataSource){
        this.iView = iView;
        repository = MealsRepositoryImpl.getInstance(localDataSource,mealsRemoteDataSource);
        this.firebaseAuthManager = new FirebaseAuthManager(FirebaseAuth.getInstance());
    }
    public MealDetailsPresenter(IWeeklyPlanMealDetails iView , MealsLocalDataSource localDataSource){
        iWeeklyPlanMealDetails = iView;
        repository = MealsRepositoryImpl.getInstance(localDataSource);
        this.firebaseAuthManager = new FirebaseAuthManager(FirebaseAuth.getInstance());
    }
    public MealDetailsPresenter(IMealCard iview, MealsLocalDataSource mealsLocalDataSource){
        this.iMealCard=iview;
        repository = MealsRepositoryImpl.getInstance(mealsLocalDataSource);
    }
    public void getMealByID(String id){
        repository.getMealById(this,id);
    }
    public void addToFav(Meal meal){
        repository.insertMealToFav(meal);
    }
    public void addToPlan(WeeklyPlanMeal meal , Meal mealDetails){
        WeeklyPlanMealDetails w = WeeklyPlanMealDetails.convertFromMeal(mealDetails);
        repository.insertPlanMeal(meal , w);

    }
    public void getMealLocal(String id){

        new Thread(()->{
            WeeklyPlanMealDetails  meal= repository.getMealByID(id);
            iWeeklyPlanMealDetails.getPlanMeal(meal);

        }).start();
    }

    public FirebaseUser getCurrentUserType() {
        return firebaseAuthManager.getCurrentUser();
    }

    public  String extractVideoId(String url) {
        String videoId = null;

        if (url != null && url.contains("v=")) {
            int index = url.indexOf("v=") + 2;
            videoId = url.substring(index);

            int ampersandIndex = videoId.indexOf("&");
            if (ampersandIndex != -1) {
                videoId = videoId.substring(0, ampersandIndex);
            }
        }

        return videoId;
    }

    @Override
    public void onSuccessResult(List<Meal> meals) {
        Log.i("TAG", "onSuccessResult: "+meals.size());
        iView.getMeal(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) {

    }
}
