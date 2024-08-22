package com.example.androidproject.view.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidproject.R;
import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.model.categoriesModel.Category;
import com.example.androidproject.model.countriesModel.Country;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.presenter.HomePresenter;
import com.example.androidproject.view.category_card.CategoryCardAdapter;
import com.example.androidproject.view.country_card.CountryCardAdapter;
import com.example.androidproject.view.meal_card.IMealCard;
import com.example.androidproject.view.meal_card.MealCardAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements IMealCard {
    HomePresenter presenter;
    RecyclerView recyclerView , recyclerViewRandom , recyclerViewCategory ,recyclerViewCountry;
    MealCardAdapter adapter , adapter2 ;
    CategoryCardAdapter adapterCategory;
    CountryCardAdapter countryCardAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new HomePresenter(this , MealsLocalDataSource.getInstance(this.getContext()));
        presenter.getRandomMeal();

        recyclerViewRandom = view.findViewById(R.id.cardRecyclerRandom);
        recyclerViewRandom.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 =new LinearLayoutManager(view.getContext());
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewRandom.setLayoutManager(layoutManager2);
        recyclerViewRandom.setVisibility(View.VISIBLE);
        adapter2 = new MealCardAdapter(view.getContext(),new ArrayList<>());
        recyclerViewRandom.setAdapter(adapter2);

        presenter.getAllCategories();
        recyclerViewCategory = view.findViewById(R.id.categoryRecycler);
        recyclerViewCategory.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 =new LinearLayoutManager(view.getContext());
        layoutManager3.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewCategory.setLayoutManager(layoutManager3);
        recyclerViewCategory.setVisibility(View.VISIBLE);

        adapterCategory = new CategoryCardAdapter(view.getContext(),new ArrayList<>());
        recyclerViewCategory.setAdapter(adapterCategory);

        presenter.listAllCountries();
        recyclerViewCountry = view.findViewById(R.id.countriesRecycler);
        recyclerViewCountry.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(),2,RecyclerView.VERTICAL,false);
        //LinearLayoutManager layoutManagerCountry =new LinearLayoutManager(view.getContext());
        //layoutManagerCountry.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewCountry.setLayoutManager(gridLayoutManager);
        recyclerViewCountry.setVisibility(View.VISIBLE);

        countryCardAdapter = new CountryCardAdapter(view.getContext(),new ArrayList<>());
        recyclerViewCountry.setAdapter(countryCardAdapter);

        presenter.getMeals();
        recyclerView = view.findViewById(R.id.cardRecycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager =new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setVisibility(View.VISIBLE);
       adapter = new MealCardAdapter(view.getContext(),new ArrayList<>());
        recyclerView.setAdapter(adapter);



    }

    @Override
    public void showData(List<Meal> meals) {

        adapter = new MealCardAdapter(this.getContext(),meals);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void random(List<Meal> meals) {
        adapter2 = new MealCardAdapter(this.getContext(),meals);
        recyclerViewRandom.setAdapter(adapter2);
    }

    @Override
    public void showCtegories(List<Category> categories) {
        adapterCategory = new CategoryCardAdapter(this.getContext(),categories);
        recyclerViewCategory.setAdapter(adapterCategory);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showCountries(List<Meal> countries) {
        Log.i("API+Client", "showCountries: from home fragment");
        countryCardAdapter = new CountryCardAdapter(this.getContext(),countries);
        recyclerViewCountry.setAdapter(countryCardAdapter);
        adapter.notifyDataSetChanged();
    }
}