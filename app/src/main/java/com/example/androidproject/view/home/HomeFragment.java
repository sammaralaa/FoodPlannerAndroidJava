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
import android.widget.Toast;

import com.example.androidproject.R;
import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.model.categoriesModel.Category;
import com.example.androidproject.model.countriesModel.Country;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.network.FirebaseAuthManager;
import com.example.androidproject.presenter.HomePresenter;
import com.example.androidproject.presenter.MealDetailsPresenter;
import com.example.androidproject.view.category_card.CategoryCardAdapter;
import com.example.androidproject.view.country_card.CountryCardAdapter;
import com.example.androidproject.view.favorites.OnFavClickListener;
import com.example.androidproject.view.meal_card.IMealCard;
import com.example.androidproject.view.meal_card.MealCardAdapter;
import com.google.firebase.auth.FirebaseUser;
import com.jakewharton.threetenabp.AndroidThreeTen;


import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements IMealCard , OnHomeFavClickListener {
    HomePresenter presenter;
    RecyclerView recyclerView , recyclerViewRandom , recyclerViewCategory ,recyclerViewCountry;
    MealCardAdapter adapter , adapter2 ;
    CategoryCardAdapter adapterCategory;
    CountryCardAdapter countryCardAdapter;
    MealDetailsPresenter mealDetailsPresenter;
    FirebaseAuthManager firebaseAuthManager = new FirebaseAuthManager();
    FirebaseUser user;
    String RandomMealID;
//    LocalDate currentDate = LocalDate.now();
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//    String formattedDate = currentDate.format(formatter);
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this.getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("mealID",RandomMealID);
        Log.i("save", "onSaveInstanceState: " +RandomMealID);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         user = firebaseAuthManager.getCurrentUser();

        presenter = new HomePresenter(this , MealsLocalDataSource.getInstance(this.getContext()));
        if(savedInstanceState != null){
            String id = savedInstanceState.getString("mealID");
            presenter.getMealById(id);
            Log.i("not null", "onViewCreated: " + id);
        }else{
            presenter.getRandomMeal();
            Log.i("TAG", "onViewCreated: saved null");
        }
        recyclerViewRandom = view.findViewById(R.id.cardRecyclerRandom);
        recyclerViewRandom.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 =new LinearLayoutManager(view.getContext());
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewRandom.setLayoutManager(layoutManager2);
        recyclerViewRandom.setVisibility(View.VISIBLE);
        adapter2 = new MealCardAdapter(view.getContext(),new ArrayList<>(),this);
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
       adapter = new MealCardAdapter(view.getContext(),new ArrayList<>(),this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void showData(List<Meal> meals) {
        adapter = new MealCardAdapter(this.getContext(),meals,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void random(List<Meal> meals) {
        RandomMealID = meals.get(0).idMeal;
        adapter2 = new MealCardAdapter(this.getContext(),meals,this);
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

    @Override
    public void onFaveMealClick(Meal meal) {
        mealDetailsPresenter = new MealDetailsPresenter(this,MealsLocalDataSource.getInstance(this.getContext()));
        if(user.isAnonymous()){
            Toast.makeText(this.getContext(), "you need to login first", Toast.LENGTH_SHORT).show();
        }
        else{
            presenter.addToFav(meal);
            Toast.makeText(this.getContext(), "Added to your favorites successfully", Toast.LENGTH_SHORT).show();
        }
    }

}