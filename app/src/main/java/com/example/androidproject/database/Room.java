package com.example.androidproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMeal;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDao;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetails;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetailsDao;
import com.example.androidproject.model.mealsModel.Meal;

@Database(entities = {Meal.class , WeeklyPlanMeal.class , WeeklyPlanMealDetails.class},version = 9)
public abstract class Room extends RoomDatabase {
    private static Room instance =null;
    public abstract MealDAO getMealDao();
    public abstract WeeklyPlanMealDao getWeeklyPlanMealDao();
    public abstract WeeklyPlanMealDetailsDao getWeeklyPlanMealDetailsDao();
    public static synchronized Room getInstance(Context context){
        if(instance==null){
            //instance = androidx.room.Room.databaseBuilder(context.getApplicationContext(),Room.class,"mealsdb").build();
            instance = androidx.room.Room.databaseBuilder(context.getApplicationContext(),
                            Room.class, "mealsdb")
                    .fallbackToDestructiveMigration() // Enables destructive migration
                    .build();
        }
        return instance;

    }
}
