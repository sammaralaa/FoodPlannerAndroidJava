package com.example.androidproject.view.weekly_plan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidproject.R;
import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMeal;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.presenter.FavoriteMealsPresenter;
import com.example.androidproject.presenter.MealsInPlanPresenter;
import com.example.androidproject.view.favorites.FavMealsAdapter;

import java.util.ArrayList;
import java.util.List;


public class PlanFragment extends Fragment implements IWeeklyPlan , OnDeleteMealPlanListener {
    MealsInPlanPresenter presenter;
    RecyclerView recyclerView;
    WeeklyPlanAdapter adapter;

    public PlanFragment() {
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
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new MealsInPlanPresenter(this , MealsLocalDataSource.getInstance(this.getContext()));
        presenter.getLocalMeals();

        recyclerView = view.findViewById(R.id.planRecycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager =new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setVisibility(View.VISIBLE);
        adapter = new WeeklyPlanAdapter(view.getContext(),new ArrayList<>(),this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getPlanMeals(LiveData<List<WeeklyPlanMeal>> meals) {
        meals.observe(this, new Observer<List<WeeklyPlanMeal>>() {
            @Override
            public void onChanged(List<WeeklyPlanMeal> meals1) {
                adapter.setList(meals1);
                adapter.notifyDataSetChanged();
            }
        });
         }

    @Override
    public void onDeletePlanMealClick(WeeklyPlanMeal meal) {
        presenter.deleteLocalMeal(meal);
        adapter.notifyDataSetChanged();

    }
}