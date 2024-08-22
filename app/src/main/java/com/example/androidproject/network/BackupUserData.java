package com.example.androidproject.network;


import android.util.Log;

import com.example.androidproject.database.MealDAO;
import com.example.androidproject.database.Room;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMeal;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDao;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetails;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetailsDao;
import com.example.androidproject.model.mealsModel.Meal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.List;
import java.util.concurrent.Executors;

public class BackupUserData {
    private final FirebaseFirestore firestore;
    private final MealDAO mealDAO;
    private final WeeklyPlanMealDao weeklyPlanMealDao;
    private final WeeklyPlanMealDetailsDao weeklyPlanMealDetailsDao;
    private final FirebaseAuth auth;

    public BackupUserData(MealDAO mealDAO, WeeklyPlanMealDao myWeekDao,WeeklyPlanMealDetailsDao weeklyPlanMealDetailsDao) {
        firestore = FirebaseFirestore.getInstance();
        this.mealDAO = mealDAO;
        this.weeklyPlanMealDao = myWeekDao;
        this.weeklyPlanMealDetailsDao = weeklyPlanMealDetailsDao;
        auth = FirebaseAuth.getInstance();

    }

    public void backupDataToFirestore() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();

            // Backup FavMeals
            CollectionReference collectionRef = firestore.collection("users")
                    .document(userId)
                    .collection("FavMeals");

            collectionRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    WriteBatch batch = firestore.batch();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        batch.delete(document.getReference());
                    }
                    batch.commit().addOnCompleteListener(deleteTask -> {
                        if (deleteTask.isSuccessful()) {
                            Executors.newSingleThreadExecutor().execute(() -> {
                                List<Meal> items = mealDAO.getAllMealsForBackup();
                                for (Meal item : items) {
                                    collectionRef.document(item.idMeal).set(item)
                                            .addOnSuccessListener(aVoid -> Log.d("FavMealsBackup", "Data backed up successfully - Meal : " + item.idMeal))
                                            .addOnFailureListener(e -> Log.e("FavMealsBackup", "Error backing up data", e));
                                }
                            });
                        } else {
                            Log.e("FavMealsBackup", "Error deleting old data", deleteTask.getException());
                        }
                    });
                } else {
                    Log.e("FavMealsBackup", "Error getting documents for deletion: ", task.getException());
                }
            });

            // Backup WeekPlan
            CollectionReference collectionRefWeek = firestore.collection("users")
                    .document(userId)
                    .collection("WeekPlan");

            collectionRefWeek.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    WriteBatch batch = firestore.batch();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        batch.delete(document.getReference());
                    }
                    batch.commit().addOnCompleteListener(deleteTask -> {
                        if (deleteTask.isSuccessful()) {
                            Executors.newSingleThreadExecutor().execute(() -> {
                                List<WeeklyPlanMeal> itemsWeek = weeklyPlanMealDao.getAllPlanMealsforBackup();
                                for (WeeklyPlanMeal item : itemsWeek) {
                                    collectionRefWeek.document(item.getMealID()).set(item)
                                            .addOnSuccessListener(aVoid -> Log.d("WeekPlanBackup", "Data backed up successfully - Meal : " + item.getMealID()))
                                            .addOnFailureListener(e -> Log.e("WeekPlanBackup", "Error backing up data", e));
                                }
                            });
                        } else {
                            Log.e("WeekPlanBackup", "Error deleting old data", deleteTask.getException());
                        }
                    });
                } else {
                    Log.e("WeekPlanBackup", "Error getting documents for deletion: ", task.getException());
                }
            });

            // Backup WeekPlanDetails
            CollectionReference collectionRefWeekPlanDetails = firestore.collection("users")
                    .document(userId)
                    .collection("WeekPlanDetails");

            collectionRefWeekPlanDetails.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    WriteBatch batch = firestore.batch();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        batch.delete(document.getReference());
                    }
                    batch.commit().addOnCompleteListener(deleteTask -> {
                        if (deleteTask.isSuccessful()) {
                            Executors.newSingleThreadExecutor().execute(() -> {
                                List<WeeklyPlanMealDetails> itemsWeek = weeklyPlanMealDetailsDao.getAllPlanMealsforBackup();
                                for (WeeklyPlanMealDetails item : itemsWeek) {
                                    collectionRefWeek.document(item.idMeal).set(item)
                                            .addOnSuccessListener(aVoid -> Log.d("WeekPlanBackup", "Data backed up successfully - Meal : " + item.idMeal))
                                            .addOnFailureListener(e -> Log.e("WeekPlanBackup", "Error backing up data", e));
                                }
                            });
                        } else {
                            Log.e("WeekPlanBackup", "Error deleting old data", deleteTask.getException());
                        }
                    });
                } else {
                    Log.e("WeekPlanBackup", "Error getting documents for deletion: ", task.getException());
                }
            });
        }
    }
    public void restoreDataFromFirestore() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();

            Executors.newSingleThreadExecutor().execute(mealDAO::deleteAll);

            CollectionReference collectionRef = firestore.collection("users")
                    .document(userId)
                    .collection("FavMeals");

            collectionRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Meal item = document.toObject(Meal.class);
                        Executors.newSingleThreadExecutor().execute(() -> {
                            mealDAO.insertMany(item);
                        });
                    }
                } else {
                    Log.e("FavMealsRestore", "Error getting documents: ", task.getException());
                }
            });

            Executors.newSingleThreadExecutor().execute(weeklyPlanMealDao::deleteAll);

            CollectionReference collectionRefWeek = firestore.collection("users")
                    .document(userId)
                    .collection("WeekPlan");

            collectionRefWeek.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        WeeklyPlanMeal item = document.toObject(WeeklyPlanMeal.class);
                        Executors.newSingleThreadExecutor().execute(() -> {
                            weeklyPlanMealDao.insertMany(item);
                        });
                    }
                } else {
                    Log.e("WeekPlanRestore", "Error getting documents: ", task.getException());
                }
            });

            Executors.newSingleThreadExecutor().execute(weeklyPlanMealDetailsDao::deleteAll);

            CollectionReference collectionRefWeekDetails = firestore.collection("users")
                    .document(userId)
                    .collection("WeekPlanDetails");

            collectionRefWeekDetails.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        WeeklyPlanMealDetails item = document.toObject(WeeklyPlanMealDetails.class);
                        Executors.newSingleThreadExecutor().execute(() -> {
                            weeklyPlanMealDetailsDao.insertMany(item);
                        });
                    }
                } else {
                    Log.e("WeekPlanRestore", "Error getting documents: ", task.getException());
                }
            });
        }
    }
}
