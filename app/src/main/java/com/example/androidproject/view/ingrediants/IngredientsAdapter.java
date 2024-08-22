package com.example.androidproject.view.ingrediants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {
    private static final String TAG = "IngredientsAdapter";
    private List<Ingredient> ingredients;
    private Context context;

    public IngredientsAdapter(Context context, List<Ingredient> items) {
        this.context = context;
        ingredients = items;

    }


    @NonNull
    @Override
    public IngredientsAdapter.IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_ingrediants, parent, false);
        return new IngredientsAdapter.IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {

        holder.ingredientName.setText(ingredients.get(position).getIngredient());
        holder.ingredientMeasure.setText(ingredients.get(position).getMeasure());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class IngredientsViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientName,ingredientMeasure;

        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);

            ingredientName = itemView.findViewById(R.id.ingredient);
            ingredientMeasure = itemView.findViewById(R.id.measurement);

        }
    }
}
