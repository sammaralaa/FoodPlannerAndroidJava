package com.example.androidproject.network.repository;

import androidx.lifecycle.LiveData;

import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMeal;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetails;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.network.MealsRemoteDataSource;
import com.example.androidproject.network.NetworkCallBack;
import com.example.androidproject.network.NetworkCallBackCategory;
import com.example.androidproject.network.NetworkCallBackCountry;

import java.util.List;

public class MealsRepositoryImpl implements MealsRepository {
    MealsLocalDataSource localDataSource;
    MealsRemoteDataSource remoteDataSource;
    private static MealsRepositoryImpl repo =null;

    private MealsRepositoryImpl(MealsLocalDataSource localDataSource, MealsRemoteDataSource remoteDataSource){
        this.localDataSource=localDataSource;
        this.remoteDataSource=remoteDataSource;
    }

    private MealsRepositoryImpl(MealsLocalDataSource localDataSource){
        this.localDataSource=localDataSource;
    }

    public static MealsRepositoryImpl getInstance(MealsLocalDataSource localDataSource ,MealsRemoteDataSource remoteDataSource){
        if(repo == null){
            repo = new MealsRepositoryImpl(localDataSource,remoteDataSource);
        }
        return repo;
    }

    public static MealsRepositoryImpl getInstance(MealsLocalDataSource localDataSource){
        if(repo == null){
            repo = new MealsRepositoryImpl(localDataSource);
        }
        return repo;
    }

    @Override
    public LiveData<List<Meal>> getLocalMeals(){
       return localDataSource.getLocalMeals();
    }

    @Override
    public void removeMealFromFav(Meal meal){
        localDataSource.removeMealFromFav(meal);
    }

    @Override
    public void insertMealToFav(Meal meal){
        localDataSource.insertMealToFav(meal);
    }

    @Override
    public void insertPlanMeal(WeeklyPlanMeal meal, WeeklyPlanMealDetails mealDetails){
        localDataSource.insertPlanMeal(meal,mealDetails);
    }

    @Override
    public LiveData<List<WeeklyPlanMeal>> getLocalPlanMeals(){
        return localDataSource.getLocalPlanMeals();
    }
    @Override
    public WeeklyPlanMealDetails getMealByID(String id){
        return localDataSource.getMealByID(id);
    }
    @Override
    public void removeMealFromPlan(WeeklyPlanMeal meal){
        localDataSource.removeMealFromPlan(meal);
    }
    ////Remote/////////////////////////////////

    @Override
    public void listAllCountries(NetworkCallBackCountry networkCallback){
        remoteDataSource.listAllCountriesCall(networkCallback);
    }
    @Override
    public void searchByCategory(NetworkCallBack networkCallback, String category){
        remoteDataSource.searchByCategoryCall(networkCallback,category);
    }

    @Override
    public void searchByCountry(NetworkCallBack networkCallback, String country){
        remoteDataSource.searchByCountryCall(networkCallback,country);
    }

    @Override
    public void searchByIngredient(NetworkCallBack networkCallback, String ingredient){
        remoteDataSource.searchByIngredientCall(networkCallback,ingredient);
    }

    @Override
    public void getRandomMeal(NetworkCallBack networkCallback){
        remoteDataSource.getRandomMealCall(networkCallback);
    }

    @Override
    public void getMealById(NetworkCallBack networkCallback, String id){
        remoteDataSource.getMealByIdCall(networkCallback,id);
    }

    @Override
    public void getAllCategories(NetworkCallBackCategory networkCallback){
        remoteDataSource.getAllCategoriesCall(networkCallback);
    }
}
