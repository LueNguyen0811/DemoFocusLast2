package com.example.demofocuslast.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.demofocuslast.Interface.SendPhoneNumberListener;
import com.example.demofocuslast.MainActivity;
import com.example.demofocuslast.R;
import com.example.demofocuslast.fragment.DecodeFragment;
import com.example.demofocuslast.fragment.SignInFragment;
import com.example.demofocuslast.fragment.SignUp2stFragment;
import com.example.demofocuslast.fragment.SignUpFragment;

public class LoginActivity extends AppCompatActivity {
    String TAG = "interface";
    ImageView imgMenu;
    Button btnSignIn, btnSignUp;
    SignUpFragment signUpFragment = new SignUpFragment();
    SignInFragment signInFragment = new SignInFragment();
    SignUp2stFragment signUp2stFragment = new SignUp2stFragment();
    DecodeFragment decodeFragment = new DecodeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
        addEvents();
    }

    private void addEvents() {
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.layoutLogin, signUpFragment).commit();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.layoutLogin, signInFragment).commit();
            }
        });
    }

    private void addControls() {
        imgMenu = findViewById(R.id.imgMenu);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
    }
}