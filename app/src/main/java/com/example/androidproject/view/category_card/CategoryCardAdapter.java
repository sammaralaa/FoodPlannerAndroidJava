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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidproject.R;
import com.example.androidproject.model.categoriesModel.Category;
import com.example.androidproject.view.home.HomeFragmentDirections;
//import com.example.androidproject.view.HomeFragmentDirections;
//import com.example.androidproject.view.HomeFragmentDirections;

import java.util.List;

public class CategoryCardAdapter extends RecyclerView.Adapter<CategoryCardAdapter.ViewHolder> {

    private final Context context;
    private List<Category> values;
    private static final String TAG = "ViewHolder Category";

    public CategoryCardAdapter(Context context,List<Category> data){
        values=data;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v =inflater.inflate(R.layout.fragment_category_card,recyclerView,false);
        CategoryCardAdapter.ViewHolder vh = new CategoryCardAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = values.get(position);
        Log.i(TAG, "onBindViewHolder: category" + category.getStrCategory());
        holder.categoryName.setText(category.getStrCategory());
        Glide.with(context).load(category.getStrCategoryThumb()).into(holder.imageView);
        Log.i(TAG, "onBindViewHolder: category"+ category.getStrCategory());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionHomeFragmentToMealsOnCategoryFragment actin = HomeFragmentDirections.actionHomeFragmentToMealsOnCategoryFragment(category.getStrCategory(),"category");
                Navigation.findNavController(view).navigate(actin);
                Log.i(TAG, "onClick: "+ category.getStrCategory());
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView ;
        TextView categoryName;
        CardView card ;
        //public ConstraintLayout constraintLayout;
        public View layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            imageView = itemView.findViewById(R.id.imageCategoryCard);
            categoryName = itemView.findViewById(R.id.txtCategoryNameCard);
            card = itemView.findViewById(R.id.categoryCard);
        }
    }
}
