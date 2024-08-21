package com.example.androidproject.network;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.androidproject.IWelcom;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthManager {

    private FirebaseAuth mAuth;
    private Activity activity;
    IWelcom iWelcom;
    public FirebaseAuthManager(Activity activity,IWelcom iWelcom) {
        this.mAuth = FirebaseAuth.getInstance();
        this.activity = activity;
        this.iWelcom=iWelcom;
    }
    public FirebaseAuthManager (){
        this.mAuth = FirebaseAuth.getInstance();
    }

    // Sign up with email and password
    public void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign up success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.i("FirebaseAuthManager", "createUserWithEmail:success");
                            Toast.makeText(activity, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign up fails, display a message to the user.
                            Log.w("FirebaseAuthManager", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(activity, "Sign Up Failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Login with email and password
    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("FirebaseAuthManager", "signInWithEmail:success");
                            Toast.makeText(activity, "Login Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            // If login fails, display a message to the user.
                            Log.w("FirebaseAuthManager", "signInWithEmail:failure", task.getException());
                            Toast.makeText(activity, "Login Failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Login anonymously
    public void loginAnonymously() {
        mAuth.signInAnonymously()
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            iWelcom.successLognAnonymously();
                           // Log.d("FirebaseAuthManager", "signInAnonymously:success");
                           //

                        } else {

                            iWelcom.failedLoginAnonymously();
                            // If login fails, display a message to the user.
                           // Log.w("FirebaseAuthManager", "signInAnonymously:failure", task.getException());
                           //
                        }
                    }
                });
    }

    // Logout
    public void logout() {
        mAuth.signOut();
        //Toast.makeText(activity, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
    }

    // Get current user
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }
}
