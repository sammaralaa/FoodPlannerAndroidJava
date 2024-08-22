package com.example.androidproject.view.login_sign;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproject.R;
import com.example.androidproject.database.MealDAO;
import com.example.androidproject.database.Room;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDao;
import com.example.androidproject.database.weeklyPlandp.WeeklyPlanMealDetailsDao;
import com.example.androidproject.network.BackupUserData;
import com.example.androidproject.view.home.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {
    EditText editName;
    EditText editPass;
    Button btnLogin;
    FirebaseAuth auth;
    String TAG = "Firebase Auth";
    String name,pass;
    WeeklyPlanMealDetailsDao weeklyPlanMealDetailsDao;
    WeeklyPlanMealDao weeklyPlanMealDao;
    MealDAO mealDAO;
    BackupUserData backupUserData;
    public LoginFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();
        mealDAO = Room.getInstance(this.getContext()).getMealDao();
        weeklyPlanMealDao = Room.getInstance(this.getContext()).getWeeklyPlanMealDao();
        weeklyPlanMealDetailsDao = Room.getInstance(this.getContext()).getWeeklyPlanMealDetailsDao();
        backupUserData = new BackupUserData(mealDAO,weeklyPlanMealDao,weeklyPlanMealDetailsDao);
        backupUserData.restoreDataFromFirestore();
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.signuptxt);
         editName = view.findViewById(R.id.nameEditText);
         editPass = view.findViewById(R.id.passwordEditText);
         btnLogin = view.findViewById(R.id.btnLogin);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (auth.getCurrentUser().isAnonymous()){
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_signUpFragment2);

                }
                else{
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment);
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = String.valueOf(editName.getText());
                pass = String.valueOf(editPass.getText());
                auth.signInWithEmailAndPassword(name, pass)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = auth.getCurrentUser();
                                    Intent intent = new Intent(view.getContext(), HomeActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(view.getContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });
            }
        });
    }
}