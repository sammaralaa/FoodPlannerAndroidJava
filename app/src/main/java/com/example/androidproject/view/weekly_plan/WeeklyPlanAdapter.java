package com.example.androidproject.view.weekly_plan;

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
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WeeklyPlanAdapter extends RecyclerView.Adapter<WeeklyPlanAdapter.ViewHolder> {

    private final Context context;
    private List<WeeklyPlanMealDetails> values;
    private static final String TAG = "ViewHolder plan";
    OnDeleteMealPlanListener listener;
    public WeeklyPlanAdapter(Context context, List<WeeklyPlanMealDetails> values , OnDeleteMealPlanListener listener) {
        this.context = context;
        this.values = values;
        this.listener=listener;
    }

    public void setList(List<WeeklyPlanMealDetails> updatedMeals){
        this.values = updatedMeals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v =inflater.inflate(R.layout.fragment_meal_on_plan_card,recyclerView,false);
        WeeklyPlanAdapter.ViewHolder vh = new WeeklyPlanAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeeklyPlanMealDetails meal = values.get(position);
        int size = values.size();
       holder.daytxt.setText(dateToDay(meal.date));
        holder.datetxt.setText(meal.date);
        holder.typetxt.setText(meal.mealType);
        holder.mealName.setText(meal.getMealName());
        Glide.with(context).load(meal.strMealThumb).into(holder.imageView);
        holder.deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDeletePlanMealClick(meal);
            }
        });
        Log.i("date", "ViewHolder: " + meal.date);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //action_planFragment_to_weeklyPlanMealDetailsFragment
                Log.i(TAG, "onClick: " + meal.getMealName());
                PlanFragmentDirections.ActionPlanFragmentToWeeklyPlanMealDetailsFragment action = PlanFragmentDirections.actionPlanFragmentToWeeklyPlanMealDetailsFragment(meal.idMeal);
                Navigation.findNavController(view).navigate(action);
//
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView , deleteImg ;
        TextView mealName , daytxt , datetxt , typetxt;
        CardView card ;
        public View layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            imageView = itemView.findViewById(R.id.pMealImg);
            mealName = itemView.findViewById(R.id.pMealNametxt);
            daytxt = itemView.findViewById(R.id.dayText);
            datetxt = itemView.findViewById(R.id.dateText);
            deleteImg = itemView.findViewById(R.id.deleteMealPlan);
            typetxt = itemView.findViewById(R.id.mealTypeText);
            card = itemView.findViewById(R.id.planMealCard);
        }
    }

    public String dateToDay(String dateString){
        String dayOfWeek="";
        // Format: YYYY-MM-DD
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());

        try {
            Date date = dateFormat.parse(dateString);
             dayOfWeek = dayFormat.format(date);
           // Output: Wednesday
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayOfWeek;
    }
}
