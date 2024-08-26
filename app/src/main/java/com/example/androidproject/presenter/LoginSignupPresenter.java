package com.example.androidproject.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.androidproject.database.MealDAO;
import com.example.androidproject.database.Room;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDao;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetailsDao;
import com.example.androidproject.network.BackupUserData;
import com.example.androidproject.network.FirebaseAuthManager;
import com.example.androidproject.view.login_sign.ILogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginSignupPresenter {

    BackupUserData backupUserData;
    ILogin iLogin;
    String TAG = "loginSignupPresenter";
    FirebaseAuthManager firebaseAuthManager;

    public LoginSignupPresenter(ILogin iLogin){
        firebaseAuthManager = new FirebaseAuthManager(FirebaseAuth.getInstance());
        this.iLogin=iLogin;
    }

    public void loginWithUsernameAndPass(String userName,String password){
        Boolean loginResult = firebaseAuthManager.login(userName,password);
        if(loginResult){
            iLogin.loginSuccess();
            Log.i(TAG, "signInWithEmail:success");
        }
        else{
            Log.i(TAG, "signInWithEmail:failure");
            iLogin.loginFaild("Login Faild");
        }

    }
    public void setBackupUserData(MealDAO mealDAO, WeeklyPlanMealDao weeklyPlanMealDao, WeeklyPlanMealDetailsDao weeklyPlanMealDetailsDao){
        backupUserData = new BackupUserData(mealDAO,weeklyPlanMealDao,weeklyPlanMealDetailsDao);
        backupUserData.restoreDataFromFirestore();
    }
}
