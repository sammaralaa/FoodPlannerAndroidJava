package com.example.androidproject.presenter;

import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.view.favorites.IFavorite;

public class FavoriteMealsPresenter {
    IFavorite iView;
    MealsLocalDataSource mealsLocalDataSource;

   public  FavoriteMealsPresenter(IFavorite iView,MealsLocalDataSource mealsLocalDataSource){
        this.iView = iView;
        this.mealsLocalDataSource = mealsLocalDataSource;
    }
    public void getLocalMeals(){
      iView.showFavMeals( mealsLocalDataSource.getLocalMeals());
    }

    public void deleteMealFromFav(Meal meal){
       mealsLocalDataSource.removeMealFromFav(meal);
    }
}
