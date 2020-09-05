package com.example.demofocuslast.common;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.demofocuslast.MainActivity;
import com.example.demofocuslast.R;
import com.example.demofocuslast.adapter.SliderAdapter;

public class OnBroading extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button btnGetStarted;
    Animation animation;
    int currentPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remove tabar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_on_broading);

        //Hooks
        viewPager = findViewById(R.id.viewPager);
        dotsLayout = findViewById(R.id.dots);
        btnGetStarted = findViewById(R.id.btnGetStarted);

        //call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    public void skip(View view){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    public void next(View view){
        viewPager.setCurrentItem(currentPost+1);
    }
    public void getStartTed(View view){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    private void addDots(int posittion){

        dots = new TextView[4];
        dotsLayout.removeAllViews();


        for (int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if (dots.length>0){
            dots[posittion].setTextColor(getResources().getColor(R.color.colorPrimary));
        }

    }
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPost = position;
            switch (position){
                case 0:
                    btnGetStarted.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    btnGetStarted.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    btnGetStarted.setVisibility(View.INVISIBLE);
                    break;
                default:
                    animation = AnimationUtils.loadAnimation(OnBroading.this,R.anim.bottom_anim);
                    btnGetStarted.setAnimation(animation);
                    btnGetStarted.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}