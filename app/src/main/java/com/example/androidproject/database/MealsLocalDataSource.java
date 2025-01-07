package com.example.androidproject.database;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetails;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetailsDao;
import com.example.androidproject.model.mealsModel.Meal;

import java.util.List;

public class MealsLocalDataSource {

    private static MealsLocalDataSource mealsLocalDataSource =null;
    MealDAO mealDAO;
    WeeklyPlanMealDetailsDao weeklyPlanMealDetailsDao;
    Context context;
    Room db;
    private LiveData<List<Meal>> mealsList;
    //private LiveData<List<WeeklyPlanMeal>> weeklyPlanList;
    private LiveData<List<WeeklyPlanMealDetails>> weeklyPlanDetailsList;
    private MealsLocalDataSource(Context _context){
        context=_context;
        db = Room.getInstance(context.getApplicationContext());
        mealDAO=db.getMealDao();
       // weeklyPlanMealDao = db.getWeeklyPlanMealDao();
        weeklyPlanMealDetailsDao = db.getWeeklyPlanMealDetailsDao();
        mealsList =mealDAO.getAllMeals();
        //weeklyPlanList=weeklyPlanMealDao.getAllPlanMeals();
        weeklyPlanDetailsList = weeklyPlanMealDetailsDao.getAllPlanMeals();
    }

    public static MealsLocalDataSource getInstance(Context context){
        if(mealsLocalDataSource == null){
            mealsLocalDataSource = new MealsLocalDataSource(context.getApplicationContext());
        }
        return mealsLocalDataSource;
    }

    public LiveData<List<Meal>> getLocalMeals(){
        return mealsList;
    }



    public void removeMealFromFav(Meal meal){
        new Thread(){
            @Override
            public void run() {
                mealDAO.deleteMeal(meal);
            }
        }.start();

    }
    public void insertMealToFav(Meal meal){
        new Thread(){
            @Override
            public void run() {
                mealDAO.insertMeal(meal);
                // Log.i("products fav", "run: "+productDAO.getAllProducts().getValue().size());
            }
        }.start();
    }

    public void insertPlanMeal( WeeklyPlanMealDetails mealDetails){
        new Thread(){
            @Override
            public void run() {
                //weeklyPlanMealDao.insertMeal(meal);
                weeklyPlanMealDetailsDao.insertMeal(mealDetails);
                 Log.i("product", "run: "+mealDetails.mealType);
            }
        }.start();
    }
    public LiveData<List<WeeklyPlanMealDetails>> getLocalPlanMeals(){
       // Log.i("TAG", "getLocalPlanMeals:---- "+weeklyPlanDetailsList.getValue().size());
        return weeklyPlanDetailsList;
    }
    public WeeklyPlanMealDetails getMealByID(String id) {
        return weeklyPlanMealDetailsDao.getPlanMeal(id);
    }
    public void removeMealFromPlan(WeeklyPlanMealDetails meal ){
        new Thread(){
            @Override
            public void run() {
                //weeklyPlanMealDao.deleteMeal(meal);
                weeklyPlanMealDetailsDao.deleteMealById(meal.idMeal);
            }
        }.start();

    }
}
