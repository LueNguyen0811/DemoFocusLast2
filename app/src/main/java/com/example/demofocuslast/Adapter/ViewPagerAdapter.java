package com.example.demofocuslast.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.example.demofocuslast.Fragment.PlantsFragment;
import com.example.demofocuslast.Interface.SendImageListenner;
import com.example.demofocuslast.Model.ViewPageModel;
import com.example.demofocuslast.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<PlantsFragment> fragmentArrayList = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void addFragment(PlantsFragment plantsFragment){
        this.fragmentArrayList.add(plantsFragment);
    }

    public void updateUi(int position){
        for (int i = 0;i<fragmentArrayList.size();i++){
            if (i != position){
                fragmentArrayList.get(i).updateUi();
            }
        }
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

}
