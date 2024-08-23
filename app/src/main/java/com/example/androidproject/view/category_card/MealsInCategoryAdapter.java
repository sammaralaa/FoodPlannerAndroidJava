package com.example.androidproject.view.category_card;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidproject.R;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.view.favorites.OnFavClickListener;
import com.example.androidproject.view.meal_card.MealCardAdapter;

import java.util.List;

public class MealsInCategoryAdapter extends RecyclerView.Adapter<MealsInCategoryAdapter.ViewHolder>{
    private final Context context;
    private List<Meal> values;
    private static final String TAG = "ViewHolder";
    private OnCategoryFavListener listener;

    public MealsInCategoryAdapter(Context context, List<Meal> data, OnCategoryFavListener listener){
        values=data;
        this.context=context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public MealsInCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v =inflater.inflate(R.layout.fragment_card,recyclerView,false);
        MealsInCategoryAdapter.ViewHolder vh = new MealsInCategoryAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MealsInCategoryAdapter.ViewHolder holder, int position) {

        Meal meal = values.get(position);
        holder.mealName.setText(meal.getMealName());
        Glide.with(context).load(meal.getMealThumb()).into(holder.imageView);
        Log.i(TAG, "onBindViewHolder: "+meal.getCategory());
        holder.addToFavImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onFaveMealClick(meal);
            }
        });
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MealsInCategoryFragmentDirections.ActionMealsOnCategoryFragmentToMealDetails action = MealsInCategoryFragmentDirections.actionMealsOnCategoryFragmentToMealDetails(meal);
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView , addToFavImg;
        TextView mealName;
        CardView card ;
        public ConstraintLayout constraintLayout;
        public View layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            imageView = itemView.findViewById(R.id.imageCard);
            mealName = itemView.findViewById(R.id.txtMealNameCard);
            addToFavImg = itemView.findViewById(R.id.addtoFavCardImag);
            card = itemView.findViewById(R.id.card);
        }
    }
}
