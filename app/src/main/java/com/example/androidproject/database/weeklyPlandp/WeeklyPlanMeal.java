package com.example.androidproject.database.weeklyPlandp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weekly_plan" , primaryKeys = {"date", "type"})
public class WeeklyPlanMeal {
    @NonNull
    @ColumnInfo(name = "date")
    String date;
    @NonNull
    @ColumnInfo(name = "type")
    String mealType;
    @ColumnInfo(name = "id")
    String mealID;
    @ColumnInfo(name = "name")
    String mealName;

    @ColumnInfo(name = "image")
    String mealThump;

    public WeeklyPlanMeal(@NonNull String date, @NonNull String mealType, String mealID, String mealName, String mealThump) {
        this.date = date;
        this.mealType = mealType;

        this.mealID = mealID;
        this.mealName = mealName;
        this.mealThump = mealThump;
    }
    public WeeklyPlanMeal(){

    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    @NonNull
    public String getMealType() {
        return mealType;
    }

    public void setMealType(@NonNull String mealType) {
        this.mealType = mealType;
    }



    public String getMealID() {
        return mealID;
    }

    public void setMealID(String mealID) {
        this.mealID = mealID;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealThump() {
        return mealThump;
    }

    public void setMealThump(String mealThump) {
        this.mealThump = mealThump;
    }
}
