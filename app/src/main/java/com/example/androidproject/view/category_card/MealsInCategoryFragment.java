package com.example.androidproject.view.category_card;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.androidproject.R;
import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.network.FirebaseAuthManager;
import com.example.androidproject.network.MealsRemoteDataSource;
import com.example.androidproject.presenter.MealDetailsPresenter;
import com.example.androidproject.presenter.MealsInCategoryPresenter;
import com.example.androidproject.view.favorites.OnFavClickListener;
import com.example.androidproject.view.mealDetails.MealDetailsArgs;
import com.example.androidproject.view.meal_card.MealCardAdapter;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


public class MealsInCategoryFragment extends Fragment implements IMealsInCategory , OnCategoryFavListener {
    MealsInCategoryPresenter presenter;
    MealsInCategoryAdapter adapter;
    RecyclerView recyclerView;
    FirebaseUser user;
    public MealsInCategoryFragment() {
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
        return inflater.inflate(R.layout.fragment_meals_in_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new MealsInCategoryPresenter(this,MealsLocalDataSource.getInstance(view.getContext()),MealsRemoteDataSource.getInstance());
        user = presenter.getCurrentUserType();
        String Name = MealsInCategoryFragmentArgs.fromBundle(getArguments()).getName();
        String Type = MealsInCategoryFragmentArgs.fromBundle(getArguments()).getType();
        if(Type.equals("category")){
            presenter.getAllMeals(Name);
        }
        else if(Type.equals("country")){
            presenter.getAllMealsOnCountry(Name);
        }

        recyclerView = view.findViewById(R.id.CategoryMealsRecycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 =new LinearLayoutManager(view.getContext());
        layoutManager2.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager2);
        recyclerView.setVisibility(View.VISIBLE);
        adapter = new MealsInCategoryAdapter(view.getContext(),new ArrayList<>(),this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMealsList(List<Meal> meals) {
        adapter = new MealsInCategoryAdapter(this.getContext(),meals,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFaveMealClick(Meal meal) {
        presenter=new MealsInCategoryPresenter(this, MealsLocalDataSource.getInstance(this.getContext()), MealsRemoteDataSource.getInstance());
        if(user.isAnonymous()){
            Toast.makeText(this.getContext(), "you need to login first", Toast.LENGTH_SHORT).show();
        }
        else{
            presenter.addToFav(meal);
            Toast.makeText(this.getContext(), "Added to your favorites successfully", Toast.LENGTH_SHORT).show();
        }
    }

}