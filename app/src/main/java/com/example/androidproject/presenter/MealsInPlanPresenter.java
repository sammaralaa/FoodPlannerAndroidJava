package com.example.androidproject.presenter;

import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMeal;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetails;
import com.example.androidproject.network.MealsRemoteDataSource;
import com.example.androidproject.network.repository.MealsRepositoryImpl;
import com.example.androidproject.view.favorites.IFavorite;
import com.example.androidproject.view.meal_card.IMealCard;
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

    public void deleteLocalMeal(WeeklyPlanMeal meal ){
            repository.removeMealFromPlan(meal);
    }
}
