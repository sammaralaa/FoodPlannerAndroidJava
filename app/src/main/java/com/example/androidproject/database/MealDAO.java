package com.example.androidproject.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetails;
import com.example.androidproject.model.mealsModel.Meal;

import java.util.List;
@Dao
public interface MealDAO {

    @Query("SELECT * FROM meals_table")
    LiveData<List<Meal>> getAllMeals();

    @Query("SELECT * FROM meals_table")
    List<Meal> getAllMealsForBackup();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(Meal meal);

    @Delete
    void deleteMeal(Meal meal);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMany(Meal... meals);

    @Query("DELETE FROM meals_table") // Replace with your table name
    void deleteAll();

}
