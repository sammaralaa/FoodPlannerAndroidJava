package com.example.androidproject.database.weeklyPlandp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.androidproject.model.mealsModel.Meal;

import java.util.List;

@Dao
public interface WeeklyPlanMealDao {
    @Query("SELECT * FROM weekly_plan")
    LiveData<List<WeeklyPlanMeal>> getAllPlanMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(WeeklyPlanMeal meal);

    @Delete
    void deleteMeal(WeeklyPlanMeal meal);

//    @Query("SELECT * FROM weekly_plan WHERE day = :day")
//    LiveData<List<WeeklyPlanMeal>> getMealsForDay(String day);
}
