package com.example.androidproject.view.login_sign;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidproject.R;
import com.example.androidproject.view.home.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUpFragment extends Fragment {
    EditText editTextUserName , editTextEmail , editTextPassword;
    Button btnSignup;
    String name , email , pass;
    String TAG="Sign-Up fragment";
    private FirebaseAuth mAuth;
    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
          //To-Do
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextUserName = view.findViewById(R.id.nameEditText2);
        editTextEmail = view.findViewById(R.id.emailEditText2);
        editTextPassword = view.findViewById(R.id.passwordEditText2);
        btnSignup = view.findViewById(R.id.btnSignup);
        mAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = String.valueOf(editTextUserName.getText());
                email = String.valueOf(editTextEmail.getText());
                pass = String.valueOf(editTextPassword.getText());
                //if(TextUtils.isEmpty(name)){return}
                Log.i(TAG, "onClick: "+name+" "+pass+" "+email);
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
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());

                                    //updateUI(null);
                                }
                            }
                        });
            }
        });
    }
}