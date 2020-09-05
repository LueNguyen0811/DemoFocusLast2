package com.example.demofocuslast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.demofocuslast.Interface.SendImageListenner;
import com.example.demofocuslast.Interface.SendTextListenner;
import com.example.demofocuslast.activity.LoginActivity;
import com.example.demofocuslast.fragment.BadgesFragment;
import com.example.demofocuslast.fragment.DecodeFragment;
import com.example.demofocuslast.fragment.HomeFragment;
import com.example.demofocuslast.fragment.LoginFragment;
import com.example.demofocuslast.fragment.SettingsFragment;
import com.example.demofocuslast.fragment.SignUp2stFragment;
import com.example.demofocuslast.fragment.StatsFragment;
import com.example.demofocuslast.fragment.TaskFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SendTextListenner, SendImageListenner {
    private static final float END_SCALE = 0.7f;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;

    TaskFragment taskFragment = new TaskFragment();
    StatsFragment statsFragment = new StatsFragment();
    BadgesFragment badgesFragment = new BadgesFragment();
    HomeFragment homeFragment = new HomeFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    LoginFragment loginFragment = new LoginFragment();
    SignUp2stFragment signUp2stFragment = new SignUp2stFragment();
    DecodeFragment decodeFragment = new DecodeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        addControls();
        navigationDrawer();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,taskFragment).commit();
        taskFragment.setSendTextListenner(this);
        homeFragment.setSendImageListenner(this);
        homeFragment.setSendTextListenner(this);
        statsFragment.setSendTextListenner(this);
    }

    private void addControls() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_navigation);
        frameLayout = findViewById(R.id.frame_layout);
    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_task);
        animateNavDrawer();
    }

    private void animateNavDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorAccent));
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                float diffScalseOffSset = slideOffset * (1 - END_SCALE);
                float offsetScale = 1 - diffScalseOffSset;
                frameLayout.setScaleX(offsetScale);
                frameLayout.setScaleY(offsetScale);

                float xOffset = drawerView.getWidth()* slideOffset;
                float xOffsetDiff = frameLayout.getWidth() * diffScalseOffSset/2;
                float xTranslation = xOffset - xOffsetDiff;
                frameLayout.setTranslationX(xTranslation);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                homeFragment = new HomeFragment();
                homeFragment.setSendImageListenner(MainActivity.this);
                homeFragment.setSendTextListenner(MainActivity.this);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_task:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,taskFragment).commit();
                break;
            case R.id.nav_Stats:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,statsFragment).commit();
                break;
            case R.id.nav_badges:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,badgesFragment).commit();
                break;
            case R.id.nav_plants:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,homeFragment).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,settingsFragment).commit();
                break;
            case R.id.nav_login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public void openDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        drawer.openDrawer(GravityCompat.START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.nav_task);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,taskFragment).commit();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
    //
//    @Override
//    public void sendText(String text) {
//        statsFragment.sendText(text);
//    }

    @Override
    public void sendText(String textNumberTask, String textNumberMinutes) {
        statsFragment.sendText(textNumberTask,textNumberMinutes);
    }

    @Override
    public void sendCent(String cent) {
//        homeFragment.receiveCent(cent);
    }

    @Override
    public void sendTotal(String textTotalTask, String textTotalMinutes) {
       badgesFragment.recieveText(textTotalTask,textTotalMinutes);
    }


    @Override
    public void sendImage(int homeImage) {
        taskFragment.sendImage(homeImage);
    }
}