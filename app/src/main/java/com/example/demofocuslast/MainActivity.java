package com.example.demofocuslast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.demofocuslast.Adapter.CommnuDraw;
import com.example.demofocuslast.Adapter.CommunicationInterface;
import com.example.demofocuslast.Fragment.BadgesFragment;
import com.example.demofocuslast.Fragment.PlantsFragment;
import com.example.demofocuslast.Fragment.SettingsFragment;
import com.example.demofocuslast.Fragment.StatsFragment;
import com.example.demofocuslast.Fragment.TaskFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CommunicationInterface {
    private static final float END_SCALE = 0.7f;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;

    TaskFragment taskFragment;
    StatsFragment statsFragment;

    private List<CommnuDraw> fragments;


    public static final String[] DRAW_FRAGMENT = {
            "Task", "Stats","Badges", "Plants","Setting"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        fragments = new ArrayList<>();
        fragments.add(StatsFragment.newInstance(this));
        fragments.add(TaskFragment.newInstance(this));
//        fragment.add(BadgesFragment.newInstance());
//        fragment.add(PlantsFragment.newInstance());
//        fragment.add(SettingsFragment.newInstance());


        addControls();
        navigationDrawer();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new TaskFragment(this)).commit();

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
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new TaskFragment(this)).commit();
                break;
            case R.id.nav_Stats:

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new StatsFragment(this)).commit();
                break;
            case R.id.nav_badges:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new BadgesFragment()).commit();
                break;
            case R.id.nav_plants:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new PlantsFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new SettingsFragment()).commit();
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
    public void onSend(Object text, Fragment fragment) {
        for(CommnuDraw f : fragments){
            if(!f.equals(fragment)) f.onReceive(text);
        }
    }
}