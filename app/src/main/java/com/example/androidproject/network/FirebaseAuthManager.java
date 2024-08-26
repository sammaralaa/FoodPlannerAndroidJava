package com.example.androidproject.network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthManager {

    private FirebaseAuth mAuth;
    boolean loginSuccess =false;
    Boolean loginAnonymouslyResult =false;
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient googleSignInClient;
    public FirebaseAuthManager(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }
    // Sign up with email and password
    public void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign up success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.i("FirebaseAuthManager", "createUserWithEmail:success");
                        } else {
                            // If sign up fails, display a message to the user.
                            Log.w("FirebaseAuthManager", "createUserWithEmail:failure", task.getException());

                        }
                    }
                });
    }

    // Login with email and password
    public Boolean login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.i("FirebaseAuthManager", "signInWithEmail:success");
                            loginSuccess = true;

                        } else {
                            // If login fails, display a message to the user.
                            loginSuccess=false;
                            Log.i("FirebaseAuthManager", "signInWithEmail:failure", task.getException());
                        }
                    }
                });
        return loginSuccess;
    }

    // Login anonymously
    public Boolean loginAnonymously() {

        mAuth.signInAnonymously()
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            loginAnonymouslyResult = true;
                        } else {
                            loginAnonymouslyResult = false;
                        }
                    }
                });
        return loginAnonymouslyResult;
    }

    private void signIn() {
//        Intent signInIntent = googleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RC_SIGN_IN) {
//            try {
//                GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class);
//                if (account != null && account.getIdToken() != null) {
//                    firebaseAuthWithGoogle(account.getIdToken());
//                } else {
//                    Log.w(TAG, "Google sign in failed: null token");
//                    Toast.makeText(activity.getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
//                }
//            } catch (Exception e) {
//                Log.w(TAG, "Google sign in failed", e);
//                Toast.makeText(activity.getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }


    // Logout
    public void logout() {
        mAuth.signOut();
    }


    // Get current user
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }
}
