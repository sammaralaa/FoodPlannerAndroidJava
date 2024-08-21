package com.example.androidproject.view.favorites;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidproject.R;
import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.presenter.FavoriteMealsPresenter;
import com.example.androidproject.view.meal_card.MealCardAdapter;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment implements IFavorite , OnFavClickListener {
    FavoriteMealsPresenter presenter;
    RecyclerView recyclerView;
    FavMealsAdapter adapter;
    public FavoriteFragment() {
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
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FavoriteMealsPresenter(this , MealsLocalDataSource.getInstance(this.getContext()));
        presenter.getLocalMeals();

        recyclerView = view.findViewById(R.id.FavRecycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager =new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setVisibility(View.VISIBLE);
        adapter = new FavMealsAdapter(view.getContext(),new ArrayList<>(),this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showFavMeals(LiveData<List<Meal>> meals) {
        meals.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                adapter.setList(meals);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onFaveMealClick(Meal meal) {
       //action_favoriteFragment_to_favMealDetailsFragment
        FavoriteFragmentDirections.ActionFavoriteFragmentToFavMealDetailsFragment action = FavoriteFragmentDirections.actionFavoriteFragmentToFavMealDetailsFragment(meal);
        Navigation.findNavController(this.getView()).navigate(action);
    }

    @Override
    public void onDeleteFavMealClick(Meal meal) {
        presenter.deleteMealFromFav(meal);
    }
}