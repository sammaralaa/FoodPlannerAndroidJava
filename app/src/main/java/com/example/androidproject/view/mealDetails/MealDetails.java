package com.example.androidproject.view.mealDetails;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.androidproject.R;
import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMeal;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.network.FirebaseAuthManager;
import com.example.androidproject.presenter.MealDetailsPresenter;
import com.example.androidproject.view.category_card.CategoryCardAdapter;
import com.example.androidproject.view.ingrediants.IngredientList;
import com.example.androidproject.view.ingrediants.IngredientsAdapter;
import com.google.firebase.auth.FirebaseUser;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MealDetails extends Fragment implements IMealDetails {
    TextView name , category , area , instructions;
    ImageView img ,datePicker;
    MealDetailsPresenter presenter;
    Meal mealFull = new Meal();
    WeeklyPlanMeal weeklyPlanMeal;
    String video_id;
    Button addToFav , addToPlan;
    Spinner mealTypeSpinner;
    String selectedDate;
     FirebaseAuthManager firebaseAuthManager = new FirebaseAuthManager();
    RecyclerView ingreRecycler;
    IngredientsAdapter ingredientsAdapter;

    public MealDetails() {
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
        return inflater.inflate(R.layout.fragment_meal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Meal meal = MealDetailsArgs.fromBundle(getArguments()).getMealData();
        FirebaseUser user = firebaseAuthManager.getCurrentUser();
        presenter = new MealDetailsPresenter(this, MealsLocalDataSource.getInstance(this.getContext()));
        presenter.getMealByID(meal.getId());
        Log.i("TAG", "onViewCreated: id = " + meal.getId());
        name = view.findViewById(R.id.txtMealName);
        category = view.findViewById(R.id.txtMealCategory);
        area = view.findViewById(R.id.txtMealArea);
        instructions = view.findViewById(R.id.txtMealInstructions);
        img = view.findViewById(R.id.imgMealThumb);
        addToFav = view.findViewById(R.id.AddToFavDetails);
        addToPlan = view.findViewById(R.id.addToPlanBtn);
        mealTypeSpinner = view.findViewById(R.id.spinnerDayOfWeek);
        datePicker = view.findViewById(R.id.calendar_icon);
        ingreRecycler = view.findViewById(R.id.ingrediantsRecycler);
        ingreRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 =new LinearLayoutManager(view.getContext());
        layoutManager3.setOrientation(RecyclerView.VERTICAL);
        ingreRecycler.setLayoutManager(layoutManager3);
        ingreRecycler.setVisibility(View.VISIBLE);

        ingredientsAdapter=new IngredientsAdapter(this.getContext(),new ArrayList<>());
        ingreRecycler.setAdapter(ingredientsAdapter);

        Glide.with(view.getContext()).load(meal.getMealThumb()).into(img);

        addToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.isAnonymous()){
                    Toast.makeText(view.getContext(), "you need to login first", Toast.LENGTH_SHORT).show();
                }
                else{
                    presenter.addToFav(mealFull);
                }

            }
        });

        addToPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.isAnonymous()){
                    Toast.makeText(view.getContext(), "you need to login first", Toast.LENGTH_SHORT).show();
                } else {
                    String mealType = mealTypeSpinner.getSelectedItem().toString();
                    //@NonNull String date, @NonNull String mealType, String dayOfWeek, String mealID, String mealName, String mealThump)
                    weeklyPlanMeal = new WeeklyPlanMeal(selectedDate, mealType, meal.getId(), meal.getMealName(), meal.getMealThumb());
                    presenter.addToPlan(weeklyPlanMeal, mealFull);
                }
            }
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Create a DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Set the selected date in the TextView
                                 selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                                Log.i("date", "onDateSet: "+selectedDate);
                                //selectedDateTextView.setText(selectedDate);
                            }
                        }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });
        final YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtubePlayerView);

        // here we are adding observer to our youtubeplayerview.
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                // loading the selected video into the YouTube Player
                youTubePlayer.loadVideo(video_id, 0);
            }

            @Override
            public void onStateChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerState state) {
                // this method is called if video has ended,
                super.onStateChange(youTubePlayer, state);
            }
        });
    }

    @Override
    public void getMeal(List<Meal> meals) {
        //mealFull.setCategory(meals.get(0).getCategory());
        mealFull = meals.get(0);
        Log.i("TAG", "showData: "+mealFull.getCategory());
        name.setText(mealFull.getMealName());
        category.setText(mealFull.getCategory());
        //Log.i("TAG", "onViewCreated: mealFull"+meal.getCategory());
        area.setText(mealFull.getOriginCountry());
        instructions.setText(mealFull.getInstructions());
        video_id = extractVideoId(mealFull.getMealVideo());
        IngredientList ingredientList = new IngredientList(mealFull);
        ingredientsAdapter=new IngredientsAdapter(this.getContext(),ingredientList.ingredients);
        ingreRecycler.setAdapter(ingredientsAdapter);
    }

    public static String extractVideoId(String url) {
        String videoId = null;

        if (url != null && url.contains("v=")) {
            int index = url.indexOf("v=") + 2;
            videoId = url.substring(index);

            int ampersandIndex = videoId.indexOf("&");
            if (ampersandIndex != -1) {
                videoId = videoId.substring(0, ampersandIndex);
            }
        }

        return videoId;
    }
}