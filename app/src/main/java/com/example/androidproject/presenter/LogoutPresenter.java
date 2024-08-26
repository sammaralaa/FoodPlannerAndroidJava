package com.example.androidproject.presenter;

import com.example.androidproject.database.MealDAO;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDao;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetailsDao;
import com.example.androidproject.network.BackupUserData;
import com.example.androidproject.network.FirebaseAuthManager;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutPresenter {
    FirebaseAuthManager firebaseAuthManager;
    BackupUserData backupUserData;

    public LogoutPresenter(){
        this.firebaseAuthManager = new FirebaseAuthManager(FirebaseAuth.getInstance());
    }

    public void Logout(){
        firebaseAuthManager.logout();
    }

    public void backupData(MealDAO mealDAO, WeeklyPlanMealDao weeklyPlanMealDao, WeeklyPlanMealDetailsDao weeklyPlanMealDetailsDao){
        backupUserData = new BackupUserData(mealDAO,weeklyPlanMealDao,weeklyPlanMealDetailsDao);
        backupUserData.backupDataToFirestore();
    }
}
