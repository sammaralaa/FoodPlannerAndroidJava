package com.example.androidproject.model.countriesModel;

import com.example.androidproject.model.mealsModel.Meal;

import java.util.List;

public class CountryResponse {
    public List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
