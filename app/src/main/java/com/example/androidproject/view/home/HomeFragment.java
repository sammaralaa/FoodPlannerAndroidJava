package com.example.androidproject.view.home;


import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
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
import com.example.androidproject.network.ConnectionCheck;
import com.example.androidproject.network.ConnectionCheckListener;
import com.example.androidproject.network.FirebaseAuthManager;
import com.example.androidproject.presenter.HomePresenter;
import com.example.androidproject.presenter.MealDetailsPresenter;
import com.example.androidproject.view.category_card.CategoryCardAdapter;
import com.example.androidproject.view.country_card.CountryCardAdapter;
import com.example.androidproject.view.favorites.OnFavClickListener;
import com.example.androidproject.view.meal_card.IMealCard;
import com.example.androidproject.view.meal_card.MealCardAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;
import com.jakewharton.threetenabp.AndroidThreeTen;


import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class HomeFragment extends Fragment implements IMealCard , OnHomeFavClickListener, ConnectionCheckListener {
    HomePresenter presenter;
    RecyclerView recyclerView , recyclerViewRandom , recyclerViewCategory ,recyclerViewCountry;
    MealCardAdapter adapter , adapter2 ;
    CategoryCardAdapter adapterCategory;
    CountryCardAdapter countryCardAdapter;
    MealDetailsPresenter mealDetailsPresenter;
    FirebaseAuthManager firebaseAuthManager = new FirebaseAuthManager();
    FirebaseUser user;
    String RandomMealID;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ConnectionCheck connectionCheck = new ConnectionCheck(this);
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_SAVED_TIME = "saved_time";
    public final static String MEAL_OF_DAY_ID = "mealId";

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
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.i("save", "onViewStateRestored: ");
        presenter = new HomePresenter(this , MealsLocalDataSource.getInstance(this.getContext()));
        if(savedInstanceState != null){
            String id = savedInstanceState.getString("mealID");
            presenter.getMealById(id);
            Log.i("not null", "onViewCreated: " + id);
        }else{
            presenter.getRandomMeal();
            Log.i("TAG", "onViewCreated: saved null");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         user = firebaseAuthManager.getCurrentUser();
        Log.i("save", "onViewCreated: ");
        presenter = new HomePresenter(this , MealsLocalDataSource.getInstance(this.getContext()));
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, 0);
        long savedTime = sharedPreferences.getLong(KEY_SAVED_TIME, 0);
        RandomMealID = sharedPreferences.getString(MEAL_OF_DAY_ID, "");


        connectionCheck = new ConnectionCheck(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            requireContext().registerReceiver(connectionCheck,filter, view.getContext().RECEIVER_NOT_EXPORTED);
        }


        if (savedTime == 0) {
            saveCurrentTimeToPreferences();
        }
        if(savedInstanceState != null){
            String id = savedInstanceState.getString("mealID");
            presenter.getMealById(id);
            Log.i("not null", "onViewCreated: " + id);
        }else{
            //presenter.getRandomMeal();
            getMealOfDay(savedTime,RandomMealID);
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
        editor = sharedPreferences.edit();
        editor.putString(MEAL_OF_DAY_ID, RandomMealID);
        editor.apply();
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
    public void onStart() {
        super.onStart();
        Log.i("save", "onStart: " );
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

    private void saveCurrentTimeToPreferences() {
        long currentTime = System.currentTimeMillis();
        editor = sharedPreferences.edit();
        editor.putLong(KEY_SAVED_TIME, currentTime);
        editor.apply();
    }

    private void getMealOfDay(long savedTime,String id) {
        boolean isOld = System.currentTimeMillis() - savedTime < TimeUnit.DAYS.toMillis(1);
        if (isOld && (!id.isEmpty())) {
            presenter.getMealById(id);
        } else {
            presenter.getRandomMeal();
            saveCurrentTimeToPreferences();
        }
    }

    @Override
    public void onChangeConnection(Boolean isConnected) {
        if(isConnected == false){
            Log.i("TAG", "onChangeConnection: lost");
            Toast.makeText(this.getContext(),"Connection lost",Toast.LENGTH_LONG);
            Navigation.findNavController(this.getView()).navigate(R.id.action_homeFragment_to_connectioLostFragment);

        }else{
            Toast.makeText(this.getContext(),"Back Online",Toast.LENGTH_LONG);
        }

    }
}