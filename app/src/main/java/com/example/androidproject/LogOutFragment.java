package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidproject.database.Room;
import com.example.androidproject.presenter.LogoutPresenter;
import com.example.androidproject.view.login_sign.MainActivity;


public class LogOutFragment extends Fragment {

    LogoutPresenter logoutPresenter;

    public LogOutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Log.i("FirebaseAuth ", "onCreate: logOut "+firebaseAuthManager.getCurrentUser().getUid());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_out, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logoutPresenter = new LogoutPresenter();
        logoutPresenter.backupData(Room.getInstance(this.getContext()).getMealDao(), Room.getInstance(this.getContext()).getWeeklyPlanMealDetailsDao());
        logoutPresenter.Logout();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
                // Start the MainActivity





        Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);

            //}
        //}, 3000);

    }
}