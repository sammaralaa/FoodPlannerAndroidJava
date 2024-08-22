package com.example.androidproject.view.mealDetails;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.google.firebase.auth.FirebaseUser;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

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
    TextView ingre1,ingre2,ingre3,ingre4,ingre5,ingre6,ingre7,ingre8,ingre9,ingre10,ingre11,ingre12,ingre13,ingre14,ingre15,ingre16,ingre17,ingre18,ingre19,ingre20;
    TextView measur1,measur2,measur3,measur4,measur5,measur6,measur7,measur8,measur9,measur10,measur11,measur12,measur13,measur14,measur15,measur16,measur17,measur18,measur19,measur20;
    FirebaseAuthManager firebaseAuthManager = new FirebaseAuthManager();


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
        ingre1 = view.findViewById(R.id.ingredient1);
        ingre2 = view.findViewById(R.id.ingredient2);
        ingre3 = view.findViewById(R.id.ingredient3);
        ingre4 = view.findViewById(R.id.ingredient4);
        ingre5 = view.findViewById(R.id.ingredient5);
        ingre6 = view.findViewById(R.id.ingredient6);
        ingre7 = view.findViewById(R.id.ingredient7);
        ingre8 = view.findViewById(R.id.ingredient8);
        ingre9 = view.findViewById(R.id.ingredient9);
        ingre10 = view.findViewById(R.id.ingredient10);
        ingre11 = view.findViewById(R.id.ingredient11);
        ingre12 = view.findViewById(R.id.ingredient12);
        ingre13 = view.findViewById(R.id.ingredient13);
        ingre14 = view.findViewById(R.id.ingredient14);
        ingre15 = view.findViewById(R.id.ingredient15);
        ingre16 = view.findViewById(R.id.ingredient16);
        ingre17 = view.findViewById(R.id.ingredient17);
        ingre18 = view.findViewById(R.id.ingredient18);
        ingre19 = view.findViewById(R.id.ingredient19);
        ingre20 = view.findViewById(R.id.ingredient20);

        measur1 = view.findViewById(R.id.measurement1);
        measur2 = view.findViewById(R.id.measurement2);
        measur3 = view.findViewById(R.id.measurement3);
        measur4 = view.findViewById(R.id.measurement4);
        measur5 = view.findViewById(R.id.measurement5);
        measur6 = view.findViewById(R.id.measurement6);
        measur7 = view.findViewById(R.id.measurement7);
        measur8 = view.findViewById(R.id.measurement8);
        measur9 = view.findViewById(R.id.measurement9);
        measur10 = view.findViewById(R.id.measurement10);
        measur11 = view.findViewById(R.id.measurement11);
        measur12 = view.findViewById(R.id.measurement12);
        measur13 = view.findViewById(R.id.measurement13);
        measur14 = view.findViewById(R.id.measurement14);
        measur15 = view.findViewById(R.id.measurement15);
        measur16 = view.findViewById(R.id.measurement16);
        measur17 = view.findViewById(R.id.measurement17);
        measur18 = view.findViewById(R.id.measurement18);
        measur19 = view.findViewById(R.id.measurement19);
        measur20 = view.findViewById(R.id.measurement20);
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
        if(mealFull.strIngredient1 != null && mealFull.strMeasure1 != null){
            ingre1.setText(mealFull.strIngredient1);
            measur1.setText(mealFull.strMeasure1);
        }
        else{
            ingre1.setVisibility(View.GONE);
            measur1.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient2 != null && mealFull.strMeasure2 != null){
            ingre2.setText(mealFull.strIngredient2);
            measur2.setText(mealFull.strMeasure2);
        }
        else{
            ingre2.setVisibility(View.GONE);
            measur2.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient3 != null && mealFull.strMeasure3 != null){
            ingre3.setText(mealFull.strIngredient3);
            measur3.setText(mealFull.strMeasure3);
        }
        else{
            ingre3.setVisibility(View.GONE);
            measur3.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient4 != null && mealFull.strMeasure4 != null){
            ingre4.setText(mealFull.strIngredient4);
            measur4.setText(mealFull.strMeasure4);
        }
        else{
            ingre4.setVisibility(View.GONE);
            measur4.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient5 != null && mealFull.strMeasure5 != null){
            ingre5.setText(mealFull.strIngredient5);
            measur5.setText(mealFull.strMeasure5);
        }
        else{
            ingre5.setVisibility(View.GONE);
            measur5.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient6 != null && mealFull.strMeasure6 != null){
            ingre6.setText(mealFull.strIngredient6);
            measur6.setText(mealFull.strMeasure6);
        }
        else{
            ingre6.setVisibility(View.GONE);
            measur6.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient7 != null && mealFull.strMeasure7 != null){
            ingre7.setText(mealFull.strIngredient7);
            measur7.setText(mealFull.strMeasure7);
        }
        else{
            ingre7.setVisibility(View.GONE);
            measur7.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient8 != null && mealFull.strMeasure8 != null){
            ingre8.setText(mealFull.strIngredient8);
            measur8.setText(mealFull.strMeasure8);
        }
        else{
            ingre8.setVisibility(View.GONE);
            measur8.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient9 != null && mealFull.strMeasure9 != null){
            ingre9.setText(mealFull.strIngredient9);
            measur9.setText(mealFull.strMeasure9);
        }
        else{
            ingre9.setVisibility(View.GONE);
            measur9.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient10 != null && mealFull.strMeasure10 != null){
            ingre10.setText(mealFull.strIngredient10);
            measur10.setText(mealFull.strMeasure10);
        }
        else{
            ingre10.setVisibility(View.GONE);
            measur10.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient11 != null && mealFull.strMeasure11 != null){
            ingre11.setText(mealFull.strIngredient11);
            measur11.setText(mealFull.strMeasure11);
        }
        else{
            ingre11.setVisibility(View.GONE);
            measur11.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient12 != null && mealFull.strMeasure12 != null){
            ingre12.setText(mealFull.strIngredient12);
            measur12.setText(mealFull.strMeasure12);
        }
        else{
            ingre12.setVisibility(View.GONE);
            measur12.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient13 != null && mealFull.strMeasure13 != null){
            ingre13.setText(mealFull.strIngredient13);
            measur13.setText(mealFull.strMeasure13);
        }
        else{
            ingre13.setVisibility(View.GONE);
            measur13.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient14 != null && mealFull.strMeasure14 != null){
            ingre14.setText(mealFull.strIngredient14);
            measur14.setText(mealFull.strMeasure14);
        }
        else{
            ingre14.setVisibility(View.GONE);
            measur14.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient15 != null && mealFull.strMeasure15 != null){
            ingre15.setText(mealFull.strIngredient15);
            measur15.setText(mealFull.strMeasure15);
        }
        else{
            ingre15.setVisibility(View.GONE);
            measur15.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient16 != null && mealFull.strMeasure16 != null){
            ingre16.setText(mealFull.strIngredient16);
            measur16.setText(mealFull.strMeasure16);
        }
        else{
            ingre16.setVisibility(View.GONE);
            measur16.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient17 != null && mealFull.strMeasure17 != null){
            ingre17.setText(mealFull.strIngredient17);
            measur17.setText(mealFull.strMeasure17);
        }
        else{
            ingre17.setVisibility(View.GONE);
            measur17.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient18 != null && mealFull.strMeasure18 != null){
            ingre18.setText(mealFull.strIngredient18);
            measur18.setText(mealFull.strMeasure18);
        }
        else{
            ingre18.setVisibility(View.GONE);
            measur18.setVisibility(View.GONE);
        }
        if(mealFull.strIngredient19 != null && mealFull.strMeasure19 != null){
            ingre19.setText(mealFull.strIngredient19);
            measur19.setText(mealFull.strMeasure19);
        }
        else{
            ingre19.setVisibility(View.GONE);
            measur19.setVisibility(View.GONE);
        }

        if(mealFull.strIngredient20 != null && mealFull.strMeasure20 != null){
            ingre20.setText(mealFull.strIngredient20);
            measur20.setText(mealFull.strMeasure20);
        }
        else{
            ingre20.setVisibility(View.GONE);
            measur20.setVisibility(View.GONE);
        }
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