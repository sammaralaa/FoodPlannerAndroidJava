package com.example.androidproject.view.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproject.R;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.network.MealsRemoteDataSource;
import com.example.androidproject.presenter.SearchPresenter;
import com.example.androidproject.view.category_card.CategoryCardAdapter;
import com.example.androidproject.view.meal_card.MealCardAdapter;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements ISearchView {
    RadioGroup radioGroup;
    RadioButton categoryButton , countryButton , ingrediantButton;
    SearchView searchView;
    SearchPresenter presenter;
    RecyclerView recyclerView;
    SearchAdapter adapter;
    TextView errormsg;
    ImageView errorImg;
    String searchType;

    public SearchFragment() {
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        radioGroup = view.findViewById(R.id.radioGroup);
        categoryButton = view.findViewById(R.id.categoryRadioButton);
        countryButton = view.findViewById(R.id.countryRadioButton2);
        ingrediantButton = view.findViewById(R.id.ingredientRadioButton3);
        searchView = view.findViewById(R.id.searchViewtxt);
        errorImg = view.findViewById(R.id.sad);
        errormsg = view.findViewById(R.id.matchtxt);
        presenter = new SearchPresenter(this, MealsRemoteDataSource.getInstance());
        recyclerView = view.findViewById(R.id.searchResaultRecycler);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager layoutManager3 =new LinearLayoutManager(view.getContext());
        layoutManager3.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager3);
        recyclerView.setVisibility(View.VISIBLE);
        adapter = new SearchAdapter(view.getContext(),new ArrayList<>());
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle the query submission
               // Toast.makeText(getContext(), "Search query: " + query, Toast.LENGTH_SHORT).show();
                int selected = radioGroup.getCheckedRadioButtonId();

                if(selected==categoryButton.getId()){
                    presenter.searchByCategory(query);
                    searchType = "category";
                }else if(selected == countryButton.getId()){
                    presenter.searchByCountry(query);
                    searchType = "country";
                }else if(selected == ingrediantButton.getId()){
                    presenter.searchByIngrediant(query);
                    searchType = "ingredient";
                }else{
                    Toast.makeText(getContext(), "Select type of Search", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
              //  recyclerView.setVisibility(View.INVISIBLE);
                // Handle the text change (e.g., filter your list in real-time)
                adapter = new SearchAdapter(view.getContext(), new ArrayList<>());
                recyclerView.setAdapter(adapter);
                errormsg.setVisibility(View.INVISIBLE);
                errorImg.setVisibility(View.INVISIBLE);
                return false;
            }

        });

    }

    @Override
    public void searchResault(List<Meal> meals) {
        if (meals != null) {
            errormsg.setVisibility(View.INVISIBLE);
            errorImg.setVisibility(View.INVISIBLE);
            adapter = new SearchAdapter(this.getContext(), meals);
            recyclerView.setAdapter(adapter);

        }
        else{
            errormsg.setVisibility(View.VISIBLE);
            errorImg.setVisibility(View.VISIBLE);
            errormsg.setText("Your search did not match any "+searchType);
            //Toast.makeText(this.getContext(), "search not found", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onSearchFailure(String msg) {

    }
}