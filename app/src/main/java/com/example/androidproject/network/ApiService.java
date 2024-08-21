package com.example.androidproject.network;

import com.example.androidproject.model.categoriesModel.CategoryResponse;
import com.example.androidproject.model.countriesModel.CountryResponse;
import com.example.androidproject.model.mealsModel.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search.php?f=a")
    Call<MealResponse> getMeals();

    @GET("list.php?a=list")
    Call<MealResponse> listAllCountries();

    @GET("categories.php")
    Call<CategoryResponse> listAllCategories();

    //Call<MealResponse> call = apiService.getMeals("chicken");<-------------------

    @GET("filter.php")
    Call<MealResponse> searchByCategory(@Query("c") String query);

    @GET("filter.php")
    Call<MealResponse> searchByCountry(@Query("a") String query);

    @GET("filter.php")
    Call<MealResponse> searchByIngredient(@Query("i") String query);

    @GET("random.php")
    Call<MealResponse> getRandomMeal();

    @GET("lookup.php")
    Call<MealResponse> getMealById(@Query("i") String query);
}
