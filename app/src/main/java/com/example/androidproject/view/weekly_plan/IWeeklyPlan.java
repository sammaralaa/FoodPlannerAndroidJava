package com.example.androidproject.view.weekly_plan;

import androidx.lifecycle.LiveData;

import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetails;

import java.util.List;

public interface IWeeklyPlan {
    public void getPlanMeals(LiveData<List<WeeklyPlanMealDetails>> meals);
}
