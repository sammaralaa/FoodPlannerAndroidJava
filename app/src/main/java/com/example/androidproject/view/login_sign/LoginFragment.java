package com.example.androidproject.view.login_sign;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproject.R;
import com.example.androidproject.database.Room;
import com.example.androidproject.presenter.LoginSignupPresenter;
import com.example.androidproject.view.home.HomeActivity;


public class LoginFragment extends Fragment implements ILogin {
    EditText editName;
    EditText editPass;
    Button btnLogin;
    LoginSignupPresenter loginSignupPresenter;
    String TAG = "Firebase Auth";
    String name,pass;

    public LoginFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        loginSignupPresenter = new LoginSignupPresenter(this);
        TextView textView = view.findViewById(R.id.signuptxt);
         editName = view.findViewById(R.id.nameEditText);
         editPass = view.findViewById(R.id.passwordEditText);
         btnLogin = view.findViewById(R.id.btnLogin);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = String.valueOf(editName.getText());
                pass = String.valueOf(editPass.getText());
                if(name.isEmpty() || pass.isEmpty()){
                    Toast.makeText(view.getContext(),"please fill empty feilds",Toast.LENGTH_SHORT);
                }else {
                    loginSignupPresenter.loginWithUsernameAndPass(name,pass);
                }
            }
        });
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(this.getContext(), HomeActivity.class);
        startActivity(intent);
        loginSignupPresenter.setRestoreUserData(Room.getInstance(this.getContext()).getMealDao(), Room.getInstance(this.getContext()).getWeeklyPlanMealDao(), Room.getInstance(this.getContext()).getWeeklyPlanMealDetailsDao());
        getActivity().finish();
    }

    @Override
    public void loginFaild(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}