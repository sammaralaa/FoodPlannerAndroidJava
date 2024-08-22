package com.example.androidproject.network;

import android.util.Log;

import com.example.androidproject.model.categoriesModel.CategoryResponse;
import com.example.androidproject.model.countriesModel.CountryResponse;
import com.example.androidproject.model.mealsModel.MealResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MealsRemoteDataSource {
        private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
        private static final String TAG = "API_Client";
        private ApiService apiService;
        private static MealsRemoteDataSource mealsRemoteDataSource = null;
        private static Retrofit retrofit;

        private MealsRemoteDataSource(){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = retrofit.create(ApiService.class);
        }
        public static MealsRemoteDataSource getInstance(){
            if(mealsRemoteDataSource == null){
                mealsRemoteDataSource = new MealsRemoteDataSource();
            }
            return mealsRemoteDataSource;
        }
        public void makeNetworkCall (NetworkCallBackCountry networkCallback){
            Call<MealResponse> call = apiService.listAllCountries();
            call.enqueue(new Callback<MealResponse>() {
                @Override
                public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                    if(response.isSuccessful()){
                        Log.i(TAG ,"onResponse: CallBack list all countries "+response.body().meals.get(0).strCategory );
                        networkCallback.onSuccessResultCountry(response.body().meals);
                       // networkCallback.onSuccessResult(response.body().getMeals());
                    }
                }
                @Override
                public void onFailure(Call<MealResponse> call, Throwable throwable) {
                    Log.i(TAG ,"onResponse: CallBack searchByCategory ");
                    networkCallback.onFailureResultCountry(throwable.getMessage());
                    throwable.printStackTrace();
                }
            });
        }

//    public void listAllCountriesCall (NetworkCallBackCountry networkCallback){
//        Call<CountryResponse> call = apiService.listAllCountries();
//        call.enqueue(new Callback<CountryResponse>() {
//            @Override
//            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
//                if(response.isSuccessful()){
//                    networkCallback.onSuccessResultCountry(response.body().countries);
//                    Log.i(TAG ,"onSuccess: CallBack listAllCountries " + response.body().getCountries().size());
//                }
//            }
//            @Override
//            public void onFailure(Call<CountryResponse> call, Throwable throwable) {
//                Log.i(TAG ,"onFailure: CallBack listAllCountries ");
//                networkCallback.onFailureResultCountry(throwable.getMessage());
//                throwable.printStackTrace();
//            }
//        });
//    }

    public void searchByCategoryCall (NetworkCallBack networkCallback , String category){
        Call<MealResponse> call = apiService.searchByCategory(category);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    //Log.i(TAG ,"onResponse: CallBack searchByCategory "+response.body().meals.size() );
                    networkCallback.onSuccessResult(response.body().meals);
                    // networkCallback.onSuccessResult(response.body().getMeals());
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG ,"onResponse: CallBack searchByCategory ");
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    public void searchByCountryCall (NetworkCallBack networkCallback , String country){
        Call<MealResponse> call = apiService.searchByCountry(country);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse: CallBack searchByCountry "+response.body() );
                    networkCallback.onSuccessResult(response.body().meals);
                    // networkCallback.onSuccessResult(response.body().getMeals());
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG ,"onResponse: CallBack searchByCountry ");
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    public void searchByIngredientCall (NetworkCallBack networkCallback , String ingredient){
        Call<MealResponse> call = apiService.searchByIngredient(ingredient);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse: CallBack searchByIngredient "+response.body() );
                    networkCallback.onSuccessResult(response.body().meals);
                    // networkCallback.onSuccessResult(response.body().getMeals());
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG ,"onResponse: CallBack searchByIngredient ");
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
    public void getRandomMealCall (NetworkCallBack networkCallback ){
        Call<MealResponse> call = apiService.getRandomMeal();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse: CallBack getRandomMeal "+response.body().meals.size() );
                    networkCallback.onSuccessResult(response.body().meals);
                    // networkCallback.onSuccessResult(response.body().getMeals());
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG ,"onResponse: CallBack getRandomMeal ");
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
    public void getMealByIdCall (NetworkCallBack networkCallback , String id){
        Call<MealResponse> call = apiService.getMealById(id);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse: CallBack get by id"+response.body().meals.size() );
                    networkCallback.onSuccessResult(response.body().meals);
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG ,"onResponse: CallBack faild to get id");
                networkCallback.onFailureResult(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
    public void getAllCategoriesCall (NetworkCallBackCategory networkCallback){
        Call<CategoryResponse> call = apiService.listAllCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG ,"onResponse: CallBack listAllCategories"+response.body().categories.size() );
                    networkCallback.onSuccessResultCategory(response.body().categories);
                }
            }
            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable throwable) {
                Log.i(TAG ,"onResponse: CallBack listAllCategories");
                networkCallback.onFailureResultCategory(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
    }

