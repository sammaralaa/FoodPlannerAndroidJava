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

import com.example.androidproject.R;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.presenter.MealsInCategoryPresenter;
import com.example.androidproject.view.mealDetails.MealDetailsArgs;
import com.example.androidproject.view.meal_card.MealCardAdapter;

import java.util.ArrayList;
import java.util.List;


public class MealsInCategoryFragment extends Fragment implements IMealsInCategory{
    MealsInCategoryPresenter presenter;
    MealsInCategoryAdapter adapter;
    RecyclerView recyclerView;
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
        String categoryName = MealsInCategoryFragmentArgs.fromBundle(getArguments()).getCategoryName();

        presenter = new MealsInCategoryPresenter(this);
        presenter.getAllMeals(categoryName);
        recyclerView = view.findViewById(R.id.CategoryMealsRecycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 =new LinearLayoutManager(view.getContext());
        layoutManager2.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager2);
        recyclerView.setVisibility(View.VISIBLE);
        adapter = new MealsInCategoryAdapter(view.getContext(),new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMealsList(List<Meal> meals) {
        adapter = new MealsInCategoryAdapter(this.getContext(),meals);
        recyclerView.setAdapter(adapter);
    }
}