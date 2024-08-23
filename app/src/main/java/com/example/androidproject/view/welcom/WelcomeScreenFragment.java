package com.example.androidproject.view.welcom;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidproject.R;
import com.example.androidproject.network.FirebaseAuthManager;
import com.example.androidproject.presenter.welcomScreenPresenter;
import com.example.androidproject.view.home.HomeActivity;


public class WelcomeScreenFragment extends Fragment implements IWelcom {

    Button login , signup,guest;
    welcomScreenPresenter presenter;
    FirebaseAuthManager firebaseAuthManager;
    public WelcomeScreenFragment() {
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
        return inflater.inflate(R.layout.fragment_welcome_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseAuthManager = new FirebaseAuthManager(this.getActivity(),this);
        login = view.findViewById(R.id.welcomLoginbtn);
        signup = view.findViewById(R.id.welcomSignupbtn);
        guest = view.findViewById(R.id.welcomGuestbtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_welcomeScreenFragment_to_loginFragment);

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_welcomeScreenFragment_to_signUpFragment);

            }
        });

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuthManager.loginAnonymously();

            }
        });
    }

    @Override
    public void successLognAnonymously() {
        Toast.makeText(this.getActivity(), "Anonymous Login Successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this.getContext(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void failedLoginAnonymously() {
        Toast.makeText(this.getActivity(), "Anonymous Login Failed: " , Toast.LENGTH_SHORT).show();
    }
}