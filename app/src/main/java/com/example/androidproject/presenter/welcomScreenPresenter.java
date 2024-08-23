package com.example.androidproject.presenter;

import com.example.androidproject.view.welcom.IWelcom;
import com.example.androidproject.network.FirebaseAuthManager;

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
