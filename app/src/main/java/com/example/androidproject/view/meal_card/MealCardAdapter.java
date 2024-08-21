package com.example.androidproject.view.meal_card;

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
//import com.example.androidproject.HomeFragmentDirections;
import com.example.androidproject.R;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.view.home.HomeFragmentDirections;
//import com.example.androidproject.view.HomeFragmentDirections;

import java.util.List;

public class MealCardAdapter extends RecyclerView.Adapter<MealCardAdapter.ViewHolder> {
    private final Context context;
    private List<Meal> values;
    private static final String TAG = "ViewHolder";

    //OnProductsClickListener listener;
    public MealCardAdapter(Context context,List<Meal> data/*,OnProductClickListener*/){
        values=data;
        this.context=context;
        //this.listener = listener
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v =inflater.inflate(R.layout.fragment_card,recyclerView,false);
        ViewHolder vh = new ViewHolder(v);
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
                //TODO
            }
        });
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionHomeFragmentToMealDetails3 actin = HomeFragmentDirections.actionHomeFragmentToMealDetails3(meal);
                Navigation.findNavController(view).navigate(actin);
                Log.i(TAG, "onClick: "+meal.getCategory());
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
