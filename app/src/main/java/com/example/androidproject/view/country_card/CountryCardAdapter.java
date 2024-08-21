package com.example.androidproject.view.country_card;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidproject.R;
import com.example.androidproject.model.countriesModel.Country;
import com.example.androidproject.model.mealsModel.Meal;
import com.example.androidproject.view.category_card.CategoryCardAdapter;
import com.example.androidproject.view.meal_card.MealCardAdapter;

import java.util.List;

public class CountryCardAdapter extends RecyclerView.Adapter<CountryCardAdapter.ViewHolder> {

    private final Context context;
    private List<Meal> values;
    private static final String TAG = "ViewHolder";
    public CountryCardAdapter(Context context,List<Meal> data/*,OnProductClickListener*/){
        values=data;
        this.context=context;
        //this.listener = listener
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v =inflater.inflate(R.layout.fragment_country,recyclerView,false);
        CountryCardAdapter.ViewHolder vh = new CountryCardAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal country = values.get(position);
        holder.categoryName.setText(country.getOriginCountry());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                HomeFragmentDirections.ActionHomeFragmentToMealDetails3 actin = HomeFragmentDirections.actionHomeFragmentToMealDetails3(meal);
//                Navigation.findNavController(view).navigate(actin);
//                Log.i(TAG, "onClick: "+meal.getCategory());
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       // private ImageView imageView ;
        TextView categoryName;
        CardView card ;
        //public ConstraintLayout constraintLayout;
        public View layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            categoryName = itemView.findViewById(R.id.countryNameCard);
            card = itemView.findViewById(R.id.countryCard);
        }
    }
}
