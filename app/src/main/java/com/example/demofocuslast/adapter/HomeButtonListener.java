package com.example.demofocuslast.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.demofocuslast.MainActivity;

public class HomeButtonListener implements View.OnClickListener {
    Activity activity;
    public HomeButtonListener(Activity activity){
        this.activity = activity;
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }
}
