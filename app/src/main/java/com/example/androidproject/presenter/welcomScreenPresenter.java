package com.example.androidproject.presenter;

import androidx.annotation.NonNull;

import com.example.androidproject.IWelcom;
import com.example.androidproject.network.FirebaseAuthManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class welcomScreenPresenter  {
    FirebaseAuthManager firebaseAuthManager;
    IWelcom iWelcom;
    welcomScreenPresenter(IWelcom iWelcom ,FirebaseAuthManager firebaseAuthManager){
        this.iWelcom = iWelcom;
        this.firebaseAuthManager = firebaseAuthManager;

    }
    public void loginAnonymously(){
       // firebaseAuthManager.loginAnonymously();

    }
}
