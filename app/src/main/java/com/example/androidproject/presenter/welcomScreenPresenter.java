package com.example.androidproject.presenter;

import com.example.androidproject.view.welcom.IWelcom;
import com.example.androidproject.network.FirebaseAuthManager;
import com.google.firebase.auth.FirebaseAuth;

public class welcomScreenPresenter  {
    FirebaseAuthManager firebaseAuthManager;
    IWelcom iWelcom;
    public welcomScreenPresenter(IWelcom iWelcom){
        this.iWelcom = iWelcom;
        this.firebaseAuthManager = new FirebaseAuthManager(FirebaseAuth.getInstance());

    }
    public void loginAnonymously(){
       // firebaseAuthManager.loginAnonymously();
        Boolean result =firebaseAuthManager.loginAnonymously();
        if(result){
            iWelcom.successLognAnonymously();
        }
        else{
            iWelcom.failedLoginAnonymously();
        }
    }
}
