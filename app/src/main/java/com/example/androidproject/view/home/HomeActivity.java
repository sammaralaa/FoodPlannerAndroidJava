package com.example.androidproject.view.home;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.androidproject.R;
import com.example.androidproject.network.FirebaseAuthManager;
import com.example.androidproject.network.MealsRemoteDataSource;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity{
    MealsRemoteDataSource mealsRemoteDataSource;
    String TAG ="callback";
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FirebaseAuthManager firebaseAuthManager = new FirebaseAuthManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseUser user = firebaseAuthManager.getCurrentUser();
        navigationView = findViewById(R.id.navigation_layout);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.list);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        //NavigationUI.setupWithNavController(navigationView,navController);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navigationView, navController);
        Menu menu = navigationView.getMenu();
        MenuItem favItem = menu.findItem(R.id.favoriteFragment);
        MenuItem PlanItem = menu.findItem(R.id.planFragment);
        if (user.isAnonymous()){
            favItem.setVisible(false);
            PlanItem.setVisible(false);
        }
        else{
            favItem.setVisible(true);
            PlanItem.setVisible(true);
        }



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawer(GravityCompat.START);
            }else{
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }


}