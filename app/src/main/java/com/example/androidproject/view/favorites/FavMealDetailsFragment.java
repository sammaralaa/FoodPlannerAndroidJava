package com.example.androidproject.view.favorites;

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

import com.bumptech.glide.Glide;
import com.example.androidproject.R;
import com.example.androidproject.database.MealsLocalDataSource;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMeal;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.network.MealsRemoteDataSource;
import com.example.androidproject.presenter.MealDetailsPresenter;
import com.example.androidproject.view.ingrediants.IngredientList;
import com.example.androidproject.view.ingrediants.IngredientsAdapter;
import com.example.androidproject.view.mealDetails.IMealDetails;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FavMealDetailsFragment extends Fragment implements IMealDetails {
    TextView name , category , area , instructions;
    ImageView img ,datePicker;
    MealDetailsPresenter presenter;
    Meal mealFull = new Meal();
    WeeklyPlanMeal weeklyPlanMeal;
    String video_id;
    Button RemoveFromFav , addToPlan;
    Spinner mealTypeSpinner;
    String selectedDate;
    RecyclerView recyclerView;
    IngredientsAdapter ingredientsAdapter;
    public FavMealDetailsFragment() {
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
        return inflater.inflate(R.layout.fragment_fav_meal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new MealDetailsPresenter(this,MealsLocalDataSource.getInstance(this.getContext()), MealsRemoteDataSource.getInstance());
        Meal meal = FavMealDetailsFragmentArgs.fromBundle(getArguments()).getMealDetails();

        name = view.findViewById(R.id.txtMealNameFav);
        category = view.findViewById(R.id.txtMealCategoryFav);
        area = view.findViewById(R.id.txtMealAreaFav);
        instructions = view.findViewById(R.id.txtMealInstructionsFav);
        img = view.findViewById(R.id.imgMealThumbFav);
        addToPlan = view.findViewById(R.id.addToPlanBtnFav);
        mealTypeSpinner = view.findViewById(R.id.spinnerDayOfWeekFav);
        datePicker = view.findViewById(R.id.calendar_iconFav);

        recyclerView = view.findViewById(R.id.FavingrediantsRecycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 =new LinearLayoutManager(view.getContext());
        layoutManager3.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager3);
        recyclerView.setVisibility(View.VISIBLE);
        ingredientsAdapter=new IngredientsAdapter(this.getContext(),new ArrayList<>());
        recyclerView.setAdapter(ingredientsAdapter);


        Glide.with(view.getContext()).load(meal.getMealThumb()).into(img);
        name.setText(meal.getMealName());
        category.setText(meal.getCategory());
        area.setText(meal.getOriginCountry());
        Log.i("ingredient", "onViewCreated: " + meal.strIngredient1);
        IngredientList ingredientList = new IngredientList(meal);
        ingredientsAdapter=new IngredientsAdapter(this.getContext(),ingredientList.ingredients);
        recyclerView.setAdapter(ingredientsAdapter);
        instructions.setText(meal.getInstructions());

        video_id = extractVideoId(meal.getMealVideo());
        addToPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mealType = mealTypeSpinner.getSelectedItem().toString();
                //@NonNull String date, @NonNull String mealType, String dayOfWeek, String mealID, String mealName, String mealThump)
                weeklyPlanMeal = new WeeklyPlanMeal(selectedDate,mealType,meal.getId(),meal.getMealName(),meal.getMealThumb());
                presenter.addToPlan(weeklyPlanMeal , mealFull);
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
        final YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtubePlayerViewFav);

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

    @Override
    public void getMeal(List<Meal> meals) {

    }
}