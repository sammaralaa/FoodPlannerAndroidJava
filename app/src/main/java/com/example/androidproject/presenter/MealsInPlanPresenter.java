package com.example.androidproject.presenter;

import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetails;
import com.example.androidproject.network.repository.MealsRepositoryImpl;
import com.example.androidproject.view.weekly_plan.IWeeklyPlan;

public class MealsInPlanPresenter {
    private IWeeklyPlan iView;
    private MealsRepositoryImpl repository;
    public MealsInPlanPresenter(IWeeklyPlan iView , MealsLocalDataSource localDataSource){
        this.iView=iView;
        repository = MealsRepositoryImpl.getInstance(localDataSource);
    }
    public MealsInPlanPresenter (MealsLocalDataSource localDataSource){
        repository = MealsRepositoryImpl.getInstance(localDataSource);
    }


    public void getLocalMeals(){
        iView.getPlanMeals(repository.getLocalPlanMeals() );
    }

    public void deleteLocalMeal(WeeklyPlanMealDetails meal ){
            repository.removeMealFromPlan(meal);
    }
}
