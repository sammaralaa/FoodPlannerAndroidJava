package com.example.androidproject.presenter;

import android.util.Log;

import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMeal;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetails;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.network.MealsRemoteDataSource;
import com.example.androidproject.network.NetworkCallBack;
import com.example.androidproject.view.mealDetails.IMealDetails;
import com.example.androidproject.view.weekly_plan.IWeeklyPlanMealDetails;

import java.util.List;

public class MealDetailsPresenter implements NetworkCallBack {

    private IMealDetails iView;
    private MealsRemoteDataSource mealsRemoteDataSource = MealsRemoteDataSource.getInstance();
    private MealsLocalDataSource localDataSource;
    IWeeklyPlanMealDetails iWeeklyPlanMealDetails;


    public MealDetailsPresenter(IMealDetails iView , MealsLocalDataSource localDataSource){
        this.iView = iView;
        this.localDataSource=localDataSource;
    }
    public MealDetailsPresenter(IWeeklyPlanMealDetails iView , MealsLocalDataSource localDataSource){
        iWeeklyPlanMealDetails = iView;
        this.localDataSource=localDataSource;
    }
    public void getMealByID(String id){
        mealsRemoteDataSource.getMealByIdCall(this,id);
    }
    public void addToFav(Meal meal){
        localDataSource.insert(meal);
    }
    public void addToPlan(WeeklyPlanMeal meal , Meal mealDetails){
        WeeklyPlanMealDetails w = WeeklyPlanMealDetails.convertFromMeal(mealDetails);
        localDataSource.insertPlanMeal(meal , w);

    }
    public void getMealLocal(String id){
        iWeeklyPlanMealDetails.getPlanMeal( localDataSource.getMealByID(id));
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
