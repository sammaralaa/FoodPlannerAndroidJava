package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidproject.network.FirebaseAuthManager;
import com.example.androidproject.view.home.HomeActivity;
import com.example.androidproject.view.login_sign.MainActivity;
import com.google.firebase.auth.FirebaseUser;


public class LogOutFragment extends Fragment {

    FirebaseAuthManager firebaseAuthManager = new FirebaseAuthManager();
    public LogOutFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the MainActivity
                firebaseAuthManager.logout();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);

            }
        }, 3000);

    }
}