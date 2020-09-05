package com.example.demofocuslast.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demofocuslast.MainActivity;
import com.example.demofocuslast.R;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 3000;

    ImageView bgImage;
    TextView txtAppName,txtSlogan;

    Animation sideAnim,bottomAnim;
    SharedPreferences onBoradingScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        //Hooks
        bgImage = findViewById(R.id.bgImage);
        txtAppName = findViewById(R.id.txtAppName);
        txtSlogan = findViewById(R.id.txtSlogan);

        //Animation
        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        //set Animation on elements
        bgImage.setAnimation(sideAnim);
        txtSlogan.setAnimation(bottomAnim);
        txtAppName.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoradingScreen = getSharedPreferences("onBroadingScreen",MODE_PRIVATE);
                boolean isFistTime = onBoradingScreen.getBoolean("fistTime",true);

                if(isFistTime == true){
                    SharedPreferences.Editor editor = onBoradingScreen.edit();
                    editor.putBoolean("fistTime",false);
                    editor.commit();

                    Intent intent = new Intent(SplashScreen.this, OnBroading.class);
                    startActivity(intent);
                    finish();// huỷ activity. có tác dụng khi không destroy thì sẽ không load lại Splash_activity


                }else{
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();// huỷ activity. có tác dụng khi không destroy thì sẽ không load lại Splash_activity
                }


            }
        },SPLASH_TIMER);

    }
}