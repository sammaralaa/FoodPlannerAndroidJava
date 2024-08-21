package com.example.androidproject.view.weekly_plan;

import androidx.lifecycle.LiveData;

import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMeal;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetails;

import java.util.List;

public interface IWeeklyPlanMealDetails {
    public void getPlanMeal(WeeklyPlanMealDetails meal);
}
