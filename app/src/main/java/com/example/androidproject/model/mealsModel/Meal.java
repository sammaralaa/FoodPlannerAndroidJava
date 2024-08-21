package com.example.androidproject.model.mealsModel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = "meals_table")
public class Meal implements Serializable {
    @PrimaryKey
    @NonNull
    public String idMeal;
    @ColumnInfo(name = "name")
    public String strMeal;
   // public Object strDrinkAlternate;
    @ColumnInfo(name = "category")
    public String strCategory;
    @ColumnInfo(name = "country")
    public String strArea;
    @ColumnInfo(name = "instructions")
    public String strInstructions;
    @ColumnInfo(name = "thumb")
    public String strMealThumb;
    public String strTags;
    @ColumnInfo(name = "video")
    public String strYoutube;
    @ColumnInfo(name = "Ingredient1")
    public String strIngredient1;
    @ColumnInfo(name = "Ingredient2")
    public String strIngredient2;
    @ColumnInfo(name = "Ingredient3")
    public String strIngredient3;
    @ColumnInfo(name = "Ingredient4")
    public String strIngredient4;
    @ColumnInfo(name = "Ingredient5")
    public String strIngredient5;
    @ColumnInfo(name = "Ingredient6")
    public String strIngredient6;
    @ColumnInfo(name = "Ingredient7")
    public String strIngredient7;
    @ColumnInfo(name = "Ingredient8")
    public String strIngredient8;
    @ColumnInfo(name = "Ingredient9")
    public String strIngredient9;
    @ColumnInfo(name = "Ingredient10")
    public String strIngredient10;
    @ColumnInfo(name = "Ingredient11")
    public String strIngredient11;
    @ColumnInfo(name = "Ingredient12")
    public String strIngredient12;
    @ColumnInfo(name = "Ingredient13")
    public String strIngredient13;
    @ColumnInfo(name = "Ingredient14")
    public String strIngredient14;
    @ColumnInfo(name = "Ingredient15")
    public String strIngredient15;
    @ColumnInfo(name = "Ingredient16")
    public String strIngredient16;
    @ColumnInfo(name = "Ingredient17")
    public String strIngredient17;
    @ColumnInfo(name = "Ingredient18")
    public String strIngredient18;
    @ColumnInfo(name = "Ingredient19")
    public String strIngredient19;
    @ColumnInfo(name = "Ingredient20")
    public String strIngredient20;
    public String strMeasure1;
    public String strMeasure2;
    public String strMeasure3;
    public String strMeasure4;
    public String strMeasure5;
    public String strMeasure6;
    public String strMeasure7;
    public String strMeasure8;
    public String strMeasure9;
    public String strMeasure10;
    public String strMeasure11;
    public String strMeasure12;
    public String strMeasure13;
    public String strMeasure14;
    public String strMeasure15;
    public String strMeasure16;
    public String strMeasure17;
    public String strMeasure18;
    public String strMeasure19;
    public String strMeasure20;
    public String strSource;
    //public Object strImageSource;
    //public Object strCreativeCommonsConfirmed;
    //public Object dateModified;

    public Meal(String idMeal, String strMeal, String strCategory, String strArea, List<String> ingredients, String strInstructions, String strMealThumb, String strYoutube) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
        this.strYoutube = strYoutube;
    }
    public Meal(){

    }

    public String getId() {
        return idMeal;
    }

    public void setId(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getMealName() {
        return strMeal;
    }

    public void setMealName(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getCategory() {
        return strCategory;
    }

    public void setCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getOriginCountry() {
        return strArea;
    }

    public void setOriginCountry(String strArea) {
        this.strArea = strArea;
    }


    public String getInstructions() {
        return strInstructions;
    }

    public void setInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getMealThumb() {
        return strMealThumb;
    }

    public void setMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getMealVideo() {
        return strYoutube;
    }

    public void setMealVideo(String strYoutube) {
        this.strYoutube = strYoutube;
    }
}
