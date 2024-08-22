package com.example.androidproject.view.ingrediants;

public class Ingredient {

    String ingredient;
    String measure;

    public Ingredient(){
    }

    public Ingredient(String ingredient,String measure){
        this.ingredient = ingredient;
        this.measure = measure;

    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }


}
