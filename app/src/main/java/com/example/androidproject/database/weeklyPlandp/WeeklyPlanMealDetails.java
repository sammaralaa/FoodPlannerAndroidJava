package com.example.androidproject.database.weeklyPlandp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.androidproject.model.mealsModel.Meal;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = "weekly_plan_details")
public class WeeklyPlanMealDetails implements Serializable {

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

        public WeeklyPlanMealDetails(String idMeal, String strMeal, String strCategory, String strArea, List<String> ingredients, String strInstructions, String strMealThumb, String strYoutube) {
            this.idMeal = idMeal;
            this.strMeal = strMeal;
            this.strCategory = strCategory;
            this.strArea = strArea;
            this.strInstructions = strInstructions;
            this.strMealThumb = strMealThumb;
            this.strYoutube = strYoutube;
        }
        public WeeklyPlanMealDetails(){

        }

        public static  WeeklyPlanMealDetails convertFromMeal(Meal meal) {
                WeeklyPlanMealDetails weeklyPlanMealDetails = new WeeklyPlanMealDetails();
                weeklyPlanMealDetails.idMeal = meal.idMeal;
                weeklyPlanMealDetails.strMeal = meal.strMeal;
                weeklyPlanMealDetails.strCategory = meal.strCategory;
                weeklyPlanMealDetails.strArea = meal.strArea;
                weeklyPlanMealDetails.strInstructions = meal.strInstructions;
                weeklyPlanMealDetails.strMealThumb = meal.strMealThumb;
                weeklyPlanMealDetails.strYoutube = meal.strYoutube;
                weeklyPlanMealDetails.strTags = meal.strTags;
                weeklyPlanMealDetails.strSource = meal.strSource;

                // Map Ingredients
                weeklyPlanMealDetails.strIngredient1 = meal.strIngredient1;
                weeklyPlanMealDetails.strIngredient2 = meal.strIngredient2;
                weeklyPlanMealDetails.strIngredient3 = meal.strIngredient3;
                weeklyPlanMealDetails.strIngredient4 = meal.strIngredient4;
                weeklyPlanMealDetails.strIngredient5 = meal.strIngredient5;
                weeklyPlanMealDetails.strIngredient6 = meal.strIngredient6;
                weeklyPlanMealDetails.strIngredient7 = meal.strIngredient7;
                weeklyPlanMealDetails.strIngredient8 = meal.strIngredient8;
                weeklyPlanMealDetails.strIngredient9 = meal.strIngredient9;
                weeklyPlanMealDetails.strIngredient10 = meal.strIngredient10;
                weeklyPlanMealDetails.strIngredient11 = meal.strIngredient11;
                weeklyPlanMealDetails.strIngredient12 = meal.strIngredient12;
                weeklyPlanMealDetails.strIngredient13 = meal.strIngredient13;
                weeklyPlanMealDetails.strIngredient14 = meal.strIngredient14;
                weeklyPlanMealDetails.strIngredient15 = meal.strIngredient15;
                weeklyPlanMealDetails.strIngredient16 = meal.strIngredient16;
                weeklyPlanMealDetails.strIngredient17 = meal.strIngredient17;
                weeklyPlanMealDetails.strIngredient18 = meal.strIngredient18;
                weeklyPlanMealDetails.strIngredient19 = meal.strIngredient19;
                weeklyPlanMealDetails.strIngredient20 = meal.strIngredient20;

                // Map Measures
                weeklyPlanMealDetails.strMeasure1 = meal.strMeasure1;
                weeklyPlanMealDetails.strMeasure2 = meal.strMeasure2;
                weeklyPlanMealDetails.strMeasure3 = meal.strMeasure3;
                weeklyPlanMealDetails.strMeasure4 = meal.strMeasure4;
                weeklyPlanMealDetails.strMeasure5 = meal.strMeasure5;
                weeklyPlanMealDetails.strMeasure6 = meal.strMeasure6;
                weeklyPlanMealDetails.strMeasure7 = meal.strMeasure7;
                weeklyPlanMealDetails.strMeasure8 = meal.strMeasure8;
                weeklyPlanMealDetails.strMeasure9 = meal.strMeasure9;
                weeklyPlanMealDetails.strMeasure10 = meal.strMeasure10;
                weeklyPlanMealDetails.strMeasure11 = meal.strMeasure11;
                weeklyPlanMealDetails.strMeasure12 = meal.strMeasure12;
                weeklyPlanMealDetails.strMeasure13 = meal.strMeasure13;
                weeklyPlanMealDetails.strMeasure14 = meal.strMeasure14;
                weeklyPlanMealDetails.strMeasure15 = meal.strMeasure15;
                weeklyPlanMealDetails.strMeasure16 = meal.strMeasure16;
                weeklyPlanMealDetails.strMeasure17 = meal.strMeasure17;
                weeklyPlanMealDetails.strMeasure18 = meal.strMeasure18;
                weeklyPlanMealDetails.strMeasure19 = meal.strMeasure19;
                weeklyPlanMealDetails.strMeasure20 = meal.strMeasure20;

                // Handle any additional fields if needed
                // weeklyPlanMealDetails.strImageSource = meal.strImageSource;
                // weeklyPlanMealDetails.strCreativeCommonsConfirmed = meal.strCreativeCommonsConfirmed;
                // weeklyPlanMealDetails.dateModified = meal.dateModified;

                return weeklyPlanMealDetails;

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
