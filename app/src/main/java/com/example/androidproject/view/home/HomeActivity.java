package com.example.androidproject.view.home;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.androidproject.R;
import com.example.androidproject.network.ConnectionCheck;
import com.example.androidproject.network.ConnectionCheckListener;
import com.example.androidproject.network.FirebaseAuthManager;
import com.example.androidproject.network.MealsRemoteDataSource;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity implements ConnectionCheckListener {
    MealsRemoteDataSource mealsRemoteDataSource;
    String TAG ="save";
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FirebaseAuthManager firebaseAuthManager = new FirebaseAuthManager();
    ConnectionCheck connectionCheck;
    MenuItem PlanItem ,loginItem,logoutItem,homeItem,searchItem;
    boolean isfirst = true;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: " + " activity");
        setContentView(R.layout.activity_home);
        user = firebaseAuthManager.getCurrentUser();
        navigationView = findViewById(R.id.navigation_layout);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.list);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        connectionCheck = new ConnectionCheck(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(connectionCheck,filter, Context.RECEIVER_NOT_EXPORTED);
        }else {
            registerReceiver(connectionCheck, filter);
        }

        //NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        //NavigationUI.setupWithNavController(navigationView,navController);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navigationView, navController);
        Menu menu = navigationView.getMenu();
        MenuItem favItem = menu.findItem(R.id.favoriteFragment);
         PlanItem = menu.findItem(R.id.planFragment);
         loginItem = menu.findItem(R.id.loginFragment2);
         logoutItem = menu.findItem(R.id.logOutFragment);
         homeItem=menu.findItem(R.id.homeFragment);
         searchItem=menu.findItem(R.id.searchFragment);

        if(user != null) {
            if (user.isAnonymous()) {
                favItem.setVisible(false);
                PlanItem.setVisible(false);
                loginItem.setVisible(true);
                logoutItem.setVisible(false);
            } else if (user != null) {
                favItem.setVisible(true);
                PlanItem.setVisible(true);
                loginItem.setVisible(false);
                logoutItem.setVisible(true);
            }
        }



    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: " + " activity");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawer(GravityCompat.START);
            }else{
                drawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onChangeConnection(Boolean isConnected) {
        //if(!isfirst){
           // Toast.makeText(this, "Internet state changed", Toast.LENGTH_SHORT).show();
            if(!isConnected){
                Toast.makeText(this, "Connection lost", Toast.LENGTH_SHORT).show();
                //Navigation.findNavController(this.navigationView).navigate(R.id.favoriteFragment);

                homeItem.setVisible(false);
                searchItem.setVisible(false);
                loginItem.setVisible(false);
                logoutItem.setVisible(false);
            }else{
                Toast.makeText(this, "Back online", Toast.LENGTH_SHORT).show();

                homeItem.setVisible(true);
                searchItem.setVisible(true);
                if(user != null){
                    if(user.isAnonymous())
                        loginItem.setVisible(true);
                    else
                        logoutItem.setVisible(true);
                }
            }
      //  }
        isfirst = false;
    }
}