package com.example.androidproject.presenter;

import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMeal;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetails;
import com.example.androidproject.network.MealsRemoteDataSource;
import com.example.androidproject.view.favorites.IFavorite;
import com.example.androidproject.view.meal_card.IMealCard;
import com.example.androidproject.view.weekly_plan.IWeeklyPlan;

public class MealsInPlanPresenter {
    private IWeeklyPlan iView;
    private MealsLocalDataSource localDataSource;
    public MealsInPlanPresenter(IWeeklyPlan iView , MealsLocalDataSource localDataSource){
        this.iView=iView;
        this.localDataSource = localDataSource;
    }
    public MealsInPlanPresenter (MealsLocalDataSource localDataSource){
        this.localDataSource=localDataSource;
    }


    public void getLocalMeals(){
        iView.getPlanMeals(localDataSource.getLocalPlanMeals() );
    }

    public void deleteLocalMeal(WeeklyPlanMeal meal ){
            localDataSource.removeMealFromPlan(meal);
    }
}
