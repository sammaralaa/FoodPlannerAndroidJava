package com.example.androidproject.view;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidproject.R;
import com.example.androidproject.view.home.HomeActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient googleSignInClient;
    EditText editTextUserName , editTextEmail , editTextPassword;
    Button btnSignup;
    String name , email , pass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);
        editTextUserName = this.findViewById(R.id.nameEditText2);
        editTextEmail = this.findViewById(R.id.emailEditText2);
        editTextPassword = this.findViewById(R.id.passwordEditText2);
        btnSignup = this.findViewById(R.id.btnSignup);
        mAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = String.valueOf(editTextUserName.getText());
                email = String.valueOf(editTextEmail.getText());
                pass = String.valueOf(editTextPassword.getText());
                //if(TextUtils.isEmpty(name)){return}
                Log.i("TAG", "onClick: "+name+" "+pass+" "+email);
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(view.getContext(), "sign up successfuly", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(view.getContext(), HomeActivity.class);
                                    startActivity(intent);
                                    // updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
//                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());

                                    //updateUI(null);
                                }
                            }
                        });
            }
        });
       // Intent signInIntent = googleSignInClient.getSignInIntent();
       // startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            try {
                GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class);
                if (account != null && account.getIdToken() != null) {
                    //firebaseAuthWithGoogle(account.getIdToken());
                } else {
                    Log.w("TAG", "Google sign in failed: null token");
                    Toast.makeText(this.getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.w("TAG", "Google sign in failed", e);
                Toast.makeText(this.getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}