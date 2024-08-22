package com.example.androidproject.database;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMeal;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDao;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetails;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetailsDao;
import com.example.androidproject.model.mealsModel.Meal;

import java.util.List;
import java.util.concurrent.Future;

public class MealsLocalDataSource {

    private static MealsLocalDataSource productsRepository =null;
    MealDAO mealDAO;
    WeeklyPlanMealDao weeklyPlanMealDao;
    WeeklyPlanMealDetailsDao weeklyPlanMealDetailsDao;
    Context context;
    Room db;
    private LiveData<List<Meal>> mealsList;
    private LiveData<List<WeeklyPlanMeal>> weeklyPlanList;
    private LiveData<List<WeeklyPlanMealDetails>> weeklyPlanDetailsList;
    private MealsLocalDataSource(Context _context){
        context=_context;
        db = Room.getInstance(context.getApplicationContext());
        mealDAO=db.getMealDao();
        weeklyPlanMealDao = db.getWeeklyPlanMealDao();
        weeklyPlanMealDetailsDao = db.getWeeklyPlanMealDetailsDao();
        mealsList =mealDAO.getAllMeals();
        weeklyPlanList=weeklyPlanMealDao.getAllPlanMeals();
        weeklyPlanDetailsList = weeklyPlanMealDetailsDao.getAllPlanMeals();
    }

    public LiveData<List<Meal>> getLocalMeals(){
        return mealsList;
    }

    public static MealsLocalDataSource getInstance(Context context){
        if(productsRepository == null){
            productsRepository = new MealsLocalDataSource(context.getApplicationContext());
        }
        return productsRepository;
    }

    public void removeMealFromFav(Meal meal){
        new Thread(){
            @Override
            public void run() {
                mealDAO.deleteMeal(meal);
            }
        }.start();

    }
    public void insert(Meal meal){
        new Thread(){
            @Override
            public void run() {
                mealDAO.insertMeal(meal);
                // Log.i("products fav", "run: "+productDAO.getAllProducts().getValue().size());
            }
        }.start();
    }

    public void insertPlanMeal(WeeklyPlanMeal meal , WeeklyPlanMealDetails mealDetails){
        new Thread(){
            @Override
            public void run() {
                weeklyPlanMealDao.insertMeal(meal);
                weeklyPlanMealDetailsDao.insertMeal(mealDetails);
                // Log.i("products fav", "run: "+productDAO.getAllProducts().getValue().size());
            }
        }.start();
    }
    public LiveData<List<WeeklyPlanMeal>> getLocalPlanMeals(){
        //Log.i("TAG", "getLocalPlanMeals: "+weeklyPlanList.getValue().size());
        return weeklyPlanList;
    }
    public WeeklyPlanMealDetails getMealByID(String id) {
        return weeklyPlanMealDetailsDao.getPlanMeal(id);
    }
    public void removeMealFromPlan(WeeklyPlanMeal meal ){
        new Thread(){
            @Override
            public void run() {
                weeklyPlanMealDao.deleteMeal(meal);
                weeklyPlanMealDetailsDao.deleteMealById(meal.getMealID());
            }
        }.start();

    }
}
