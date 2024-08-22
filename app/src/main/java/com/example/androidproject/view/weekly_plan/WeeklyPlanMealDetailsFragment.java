package com.example.androidproject.view.weekly_plan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidproject.R;
import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMeal;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetails;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.presenter.MealDetailsPresenter;
import com.example.androidproject.view.ingrediants.IngredientList;
import com.example.androidproject.view.ingrediants.IngredientsAdapter;
import com.example.androidproject.view.mealDetails.MealDetailsArgs;

import java.util.ArrayList;
import java.util.List;


public class WeeklyPlanMealDetailsFragment extends Fragment implements IWeeklyPlanMealDetails {
    private static final String TAG = "WeeklyPlanMealDetailsFragment";
    TextView name , category , area , instructions;
    ImageView img ;
    MealDetailsPresenter presenter;
    WeeklyPlanMealDetails mealFull = new WeeklyPlanMealDetails();
    RecyclerView recyclerView;
    String video_id;
    IngredientsAdapter ingredientsAdapter;


    public WeeklyPlanMealDetailsFragment() {
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
        return inflater.inflate(R.layout.fragment_weekly_plan_meal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String mealId = WeeklyPlanMealDetailsFragmentArgs.fromBundle(getArguments()).getMealId();
        presenter = new MealDetailsPresenter(this, MealsLocalDataSource.getInstance(this.getContext()));
        presenter.getMealLocal(mealId);

        name = view.findViewById(R.id.txtMealNamePlan);
        category = view.findViewById(R.id.txtMealCategoryPlan);
        area = view.findViewById(R.id.txtMealAreaPlan);
        instructions = view.findViewById(R.id.txtMealInstructionsPlan);
        img = view.findViewById(R.id.imgMealThumbPlan);

        recyclerView = view.findViewById(R.id.planingrediantsRecycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 =new LinearLayoutManager(view.getContext());
        layoutManager3.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager3);
        recyclerView.setVisibility(View.VISIBLE);

        ingredientsAdapter=new IngredientsAdapter(this.getContext(),new ArrayList<>());
        recyclerView.setAdapter(ingredientsAdapter);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        name.setText(mealFull.getMealName());
        category.setText(mealFull.getCategory());
        area.setText(mealFull.getOriginCountry());
        instructions.setText(mealFull.getInstructions());
        Glide.with(this.getContext()).load(mealFull.getMealThumb()).into(img);

        IngredientList ingredientList = new IngredientList(mealFull);
        ingredientsAdapter=new IngredientsAdapter(this.getContext(),ingredientList.ingredients);
        recyclerView.setAdapter(ingredientsAdapter);
    }

    @Override
    public void getPlanMeal(WeeklyPlanMealDetails meals) {
        Log.i(TAG, "getPlanMeal: " + meals.getMealName());
        mealFull = meals;
//        name.setText(meals.getMealName());
//        category.setText(meals.getCategory());
//        area.setText(meals.getOriginCountry());
//        instructions.setText(meals.getInstructions());
//        Glide.with(this.getContext()).load(meals.getMealThumb()).into(img);
        }
}