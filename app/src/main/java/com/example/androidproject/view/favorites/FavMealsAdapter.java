package com.example.androidproject.view.favorites;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidproject.R;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.view.home.HomeFragmentDirections;
import com.example.androidproject.view.meal_card.MealCardAdapter;

import java.util.List;

public class FavMealsAdapter extends RecyclerView.Adapter<FavMealsAdapter.ViewHolder>{

    private final Context context;
    private List<Meal> values;
    private static final String TAG = "ViewHolder";
    private OnFavClickListener listener;

    public FavMealsAdapter(Context context, List<Meal> data,OnFavClickListener listener){
        values=data;
        this.context=context;
        this.listener = listener;
    }

    public void setList(List<Meal> updatedMeals){
        this.values = updatedMeals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v =inflater.inflate(R.layout.fragment_fav_meal_card,recyclerView,false);
        FavMealsAdapter.ViewHolder vh = new FavMealsAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = values.get(position);
        holder.mealName.setText(meal.getMealName());
        Glide.with(context).load(meal.getMealThumb()).into(holder.imageView);

        holder.addToFavImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDeleteFavMealClick(meal);
            }
        });
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onFaveMealClick(meal);
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
            imageView = itemView.findViewById(R.id.imageFav);
            mealName = itemView.findViewById(R.id.txtMealNameFav);
            addToFavImg = itemView.findViewById(R.id.removeFavCardImag);
            card = itemView.findViewById(R.id.cardFav);
        }
    }
}
